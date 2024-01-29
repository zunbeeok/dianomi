package com.sparta.dianomi.domain.order.service

import com.sparta.dianomi.common.exception.InvalidCredentialException
import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.order.dto.*
import com.sparta.dianomi.domain.order.model.Order
import com.sparta.dianomi.domain.order.model.OrderDelete
import com.sparta.dianomi.domain.order.model.OrderMenu
import com.sparta.dianomi.domain.order.repository.OrderDeleteRepository
import com.sparta.dianomi.domain.order.repository.OrderMenuRepository
import com.sparta.dianomi.domain.order.repository.OrderRepository
import com.sparta.dianomi.domain.store.model.Store
import com.sparta.dianomi.domain.store.repository.MenuRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class OrderServiceImpl (
    private val orderRepository: OrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
    private val menuRepository: MenuRepository,
    private val orderDeleteRepository: OrderDeleteRepository
):OrderService{

    @Transactional
    override fun createOrder(orderRequest: OrderRequest, userId: Long): OrderResponse{
        val order = this.orderRepository.save(orderRequest.toEntity(userId))
        val orderResponse=OrderResponse(orderRequest.requestPrice)

        val orderMenus= ArrayList<OrderMenu>();
        orderRequest.menuList.map {
            menuRequest->
            this.menuRepository.findByIdOrNull(menuRequest.menuId)
                .let {
                    menu->
                    menu ?: throw NullPointerException();
                }.let {
                    menu->
                    orderMenus.add(OrderMenu(order,menu,menuRequest.count))
                    orderResponse.menuList.add(MenuInfo(menu.id!!,menu.name,menu.price))
                    orderResponse.storeInfo=StoreInfo(menu.store.id!!,menu.store.name)
                    orderResponse.totalPrice += (menu.price *menuRequest.count)
                }
        }
        orderResponse.status = order.status;
        orderResponse.createdAt =order.createdAt;

        this.orderMenuRepository.saveAll(orderMenus)

        return orderResponse

    }

    @Transactional
    override fun deleteOrder(orderId: Long, orderDeleteRequest: OrderDeleteRequest, userId: Long) {
        val order:Order = this.orderRepository.findByIdOrNull(orderId) ?: throw ModelNotFoundException("order",orderId)

        if(order.isDeleted) throw  ModelNotFoundException("order",orderId);

        this.orderDeleteRepository.save(OrderDelete(order.id!!,orderDeleteRequest.comment,userId));

        order.isDeleted=true;
    }

    override fun getOrder(orderId: Long,userId: Long): OrderResponse{
        val order = this.orderRepository.findByIdOrNull(orderId) ?: throw  ModelNotFoundException("order",orderId);

        //삭제된 데이터
        if(order.isDeleted) throw  ModelNotFoundException("order",orderId);

        if(order.userId != userId) throw InvalidCredentialException()


        val store:Store = order.orderMenus[0].menu.store

        val orderResponse = OrderResponse(order.totalPrice)

        orderResponse.menuList = order.orderMenus.map {
            val menu = it.menu
            orderResponse.totalPrice += menu.price

            MenuInfo(menu.id!!,menu.name,menu.price)
        }.toMutableList()
        orderResponse.storeInfo = StoreInfo(store.id!!,store.name)
        orderResponse.status = order.status
        orderResponse.createdAt = order.createdAt

        return orderResponse;
    }
    override fun getOrderList(userId: Long): List<OrderInfo> {
        val order =this.orderRepository.findAllByUserId(userId)

        return OrderListResponse().from(order)
    }
}