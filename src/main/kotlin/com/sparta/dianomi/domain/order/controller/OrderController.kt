package com.sparta.dianomi.domain.order.controller

import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.order.dto.*
import com.sparta.dianomi.domain.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController (
    private val orderService: OrderService
){
    @GetMapping
    fun getOrderList(
        @AuthenticationPrincipal user: UserPrincipal
    ):ResponseEntity<List<OrderInfo>>
        = ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderList(user.id));


    @GetMapping("/{orderId}")
    fun getOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable orderId:Long,
    ):ResponseEntity<OrderResponse>
        = ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(orderId,user.id))


    @PostMapping()
    fun createOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody orderRequest: OrderRequest
    ):ResponseEntity<OrderResponse>
        = ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest, user.id))


    //주문취소.
    @PostMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN') ")
    fun deleteOrder(@PathVariable orderId:Long, @RequestBody orderDeleteRequest: OrderDeleteRequest,@AuthenticationPrincipal user:UserPrincipal):ResponseEntity<Unit>{
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(orderService.deleteOrder(orderId,orderDeleteRequest,user.id))
    }


//    @PutMapping("/{orderId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    fun updateDeleteOrder(@PathVariable orderId: Long, ){
//
//    }


            //어드민이어도 주문은 수정이 불가능하다.

}