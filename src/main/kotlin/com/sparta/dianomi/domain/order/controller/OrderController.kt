package com.sparta.dianomi.domain.order.controller

import com.sparta.dianomi.domain.order.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController

class OrderController (
    orderService: OrderService
){
    @GetMapping
    fun getOrderList(){
        //주문 내역들을 조회하는 사이트.
        //store name;
        //menuname :limit1
        //price
        //orderName;
        //status;
        //coupon
    }

}