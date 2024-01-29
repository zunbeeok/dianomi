package com.sparta.dianomi.domain.order.service

import com.sparta.dianomi.domain.order.dto.*

interface OrderService {

    fun createOrder(orderRequest: OrderRequest,userId:Long):OrderResponse;

    fun deleteOrder(orderId:Long,orderDeleteRequest: OrderDeleteRequest,userId: Long);

    fun getOrder(orderId: Long,userId: Long):OrderResponse;

    fun getOrderList(userId: Long):List<OrderInfo>;
}