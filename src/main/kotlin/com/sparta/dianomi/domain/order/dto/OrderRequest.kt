package com.sparta.dianomi.domain.order.dto

data class OrderRequest (
    val menuList:List<OrderMenu>,

)



data class OrderMenu(
    val count:Long,
    val menuId:Long,
)