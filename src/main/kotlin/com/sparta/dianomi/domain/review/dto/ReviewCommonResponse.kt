package com.sparta.dianomi.domain.review.dto

import com.sparta.dianomi.domain.review.model.Review
import java.time.LocalDateTime

data class ReviewCommonResponse(
    private val review: Review
){
    val rating:Long = review.rating;

    val comment:String = review.comment;

    val createdAt :LocalDateTime = review.createdAt;

    val storeName : String? =null;

    val menuName :String?=null;
}

