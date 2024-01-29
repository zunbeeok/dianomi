package com.sparta.dianomi.domain.store.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.sparta.dianomi.domain.member.model.Cart
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import jakarta.persistence.*

@Entity
@Table(name = "store")
class Store(
    @Column(name = "name")
    var name: String,
    @Column(name = "address")
    var address: String,
    @Column(name = "description")
    var description: String,
    @Column(name = "business_num")
    var businessNum: String,

    @Column(name = "user_id")
    var userId: Long,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null
    @Column(name="order_count")
    var orderCount:Int =0
    @Column(name = "review_count")
    var reviewCount:Int =0

    fun toResponse():StoreResponseDto{
        return StoreResponseDto(
            id = this.id!!,
            name = this.name,
            address = this.address,
            description =this.description,
            businessNum = this.businessNum,
            orderCount = this.orderCount,
            reviewCount = this.reviewCount,
            userId = this.userId


        )
    }
    //반환 dto menuDto 안에 프로퍼티를 entity 의 값으로 대입 하는 함수


}


