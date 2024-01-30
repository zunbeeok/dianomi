package com.sparta.dianomi.domain.review.service

import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.member.repository.MemberRepository
import com.sparta.dianomi.domain.review.dto.CreateReviewRequest
import com.sparta.dianomi.domain.review.dto.ReviewCommonResponse
import com.sparta.dianomi.domain.review.dto.UpdateReviewRequest
import com.sparta.dianomi.domain.review.model.Review
import com.sparta.dianomi.domain.review.repository.ReviewRepository
import com.sparta.dianomi.domain.store.repository.StoreRepository
import jakarta.transaction.Transactional
import jdk.jshell.spi.ExecutionControlProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service


@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val memberRepository: MemberRepository,
    private val storeRepository:StoreRepository
) : ReviewService{

    @Transactional
    override fun createReview(request: CreateReviewRequest,userId: Long): ReviewCommonResponse
    = Review(request.rating,request.description,request.orderId,request.orderId,userId)
        .let {
            reviewRepository.save(it)
        }
        .let {
            ReviewCommonResponse(it)
        }

    override fun getReviewListByMemberId(userId: Long): List<ReviewCommonResponse>
        = memberRepository.findByIdOrNull(userId).let {
            it ?: throw ModelNotFoundException("member",userId)
        }.let {
            reviewRepository.findAllByUserId(it.id!!)
        }.map {
            ReviewCommonResponse(it)
        }

    override fun getReviewListByStoreId(storeId: Long): List<ReviewCommonResponse>
        = storeRepository.findByIdOrNull(storeId).let {
            it ?: throw ModelNotFoundException("store", storeId)
        }.let {
            reviewRepository.findAllByStoreId(it.id!!)
        }.map {
            ReviewCommonResponse(it)
        }


    @Transactional
    override fun setReview(reviewId: Long, updateReviewRequest: UpdateReviewRequest, userId: Long): ReviewCommonResponse
        = reviewRepository.findByIdOrNull(reviewId).let {
            it ?: throw ModelNotFoundException("review", reviewId)
        }.also {
            it.compareMemberId(userId)
        }
        .also {
            it.updateRatingAndComment(updateReviewRequest)
            reviewRepository.save(it)
        }.let {
            ReviewCommonResponse(it)
        }




    @Transactional
    override fun deleteReview(reviewId: Long, userId: Long)
    = reviewRepository.findByIdOrNull(reviewId).let {
        it ?: throw ModelNotFoundException("review", reviewId)
    }.also{
        it.compareMemberId(userId)
    }.let{
        reviewRepository.delete(it)
    }

}