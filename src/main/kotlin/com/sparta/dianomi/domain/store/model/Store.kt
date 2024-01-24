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
    var id:Long?=null
    @Column(name="order_count")
    var orderCount:Int =0
    @Column(name = "review_count")
    var reviewCount:Int =0
    //db상의 컬럼이 id 로 되어있어서 쌍방으로 맞춰줘야함 안맞춰줘도 되는거는 따로 알아보기 잘모르겠음 ㅇㅇ

    //
    fun toResponse():StoreResponseDto{
        return StoreResponseDto(
            id = this.id!!,
            name = this.name,
            address = this.address,
            description =this.description,
            businessNum = this.businessNum,
            orderCount = this.orderCount,
            reviewCount = this.reviewCount

        )
    }
    //반환 dto menuDto 안에 프로퍼티를 entity 의 값으로 대입 하는 함수


}


