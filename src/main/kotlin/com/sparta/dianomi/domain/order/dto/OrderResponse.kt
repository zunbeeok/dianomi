package com.sparta.dianomi.domain.order.dto


import com.sparta.dianomi.domain.order.model.OrderStatus
import com.sparta.dianomi.domain.store.model.Menu
import java.time.LocalDateTime

data class OrderResponse(
    var discountPrice:Long,
){
    var totalPrice: Long = 0;
    var menuList: MutableList<MenuInfo> = arrayListOf();
    lateinit var storeInfo: StoreInfo;
    lateinit var status: OrderStatus;
    lateinit var createdAt: LocalDateTime;


}

data class MenuInfo(
    var menuId:Long,
    var menuName:String,
    var price: Int,
){
    fun from(menu:Menu){
        menuId=menu.id!!;
        menuName=menu.name;
        price = menu.price

    }
}

data class StoreInfo(
    val storeId: Long,
    val storeName: String
)
