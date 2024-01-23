package com.sparta.dianomi.domain.store.model

import com.sparta.dianomi.domain.store.dto.CreateStoreDto
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "store")
class Store(
    @Column(name = "name")
    var name:String,
    @Column(name = "address")
    var address:String,
    @Column(name = "description")
    var description:String,
    @Column(name = "business_num")
    var businessNum:String

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var storeId:Long?=null
    @Column(name="order_count")
    var orderCount:Int =0
    @Column(name = "review_count")
    var reviewCount:Int =0

    //
    fun toResponse():StoreResponseDto{
        return StoreResponseDto(
            storeId = this.storeId!!,
            name = this.name,
            address = this.address,
            description =this.description,
            businessNum = this.businessNum,
            orderCount = this.orderCount,
            reviewCount = this.reviewCount

        )
    }


}


