package com.sparta.dianomi.domain.review.service

import com.sparta.dianomi.domain.review.dto.CreateReviewRequest
import com.sparta.dianomi.domain.review.dto.ReviewCommonResponse
import com.sparta.dianomi.domain.review.dto.UpdateReviewRequest
import org.springframework.security.core.userdetails.User

interface ReviewService {

    fun createReview(createReviewRequest: CreateReviewRequest,userId:Long):ReviewCommonResponse;

    fun getReviewListByMemberId(userId:Long):List<ReviewCommonResponse>;

    fun getReviewListByStoreId(storeId:Long):List<ReviewCommonResponse>;

    fun setReview(reviewId:Long, updateReviewRequest: UpdateReviewRequest,userId: Long):ReviewCommonResponse;

    fun deleteReview(reviewId: Long,userId: Long);
}