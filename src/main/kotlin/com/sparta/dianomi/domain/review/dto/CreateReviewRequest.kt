package com.sparta.dianomi.domain.review.dto

data class CreateReviewRequest(
//    var menuId: Long,

    var orderId:Long,

    var description:String,

    var rating:Long,
)
