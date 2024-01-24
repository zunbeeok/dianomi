package com.sparta.dianomi.domain.review.repository

import com.sparta.dianomi.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository:JpaRepository<Review,Long> {

    fun findAllByUserId(userId:Long):List<Review>

    fun findAllByStoreId(storeId:Long):List<Review>
}