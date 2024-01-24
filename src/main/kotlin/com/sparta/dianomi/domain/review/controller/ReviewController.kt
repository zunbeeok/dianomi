package com.sparta.dianomi.domain.review.controller

import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.review.dto.CreateReviewRequest
import com.sparta.dianomi.domain.review.dto.ReviewCommonResponse
import com.sparta.dianomi.domain.review.dto.UpdateReviewRequest
import com.sparta.dianomi.domain.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reviews")
class ReviewController(
    val reviewService: ReviewService
) {


    //리뷰는 자기가 작성한 리뷰 밖에 못봐요.
    //로그인상태(jwt토큰이 발급된 상태)
    //jwt로부터 유저정보를 가져올 수 있다.
    @GetMapping()
    fun getReviewsByMemberId(@AuthenticationPrincipal user:UserPrincipal):ResponseEntity<List<ReviewCommonResponse>>
        = ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewListByMemberId(user.id));


    @GetMapping("/store")
    fun getReviewsByStoreId(@RequestParam storeId: Long):ResponseEntity<List<ReviewCommonResponse>>
        = ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewListByStoreId(storeId));

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    fun createReview(@RequestBody createReviewRequest: CreateReviewRequest,@AuthenticationPrincipal user:UserPrincipal):ResponseEntity<ReviewCommonResponse>{

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(createReviewRequest,user.id))
    }


    @PutMapping("/{reviewId}")
    fun setReview(@PathVariable reviewId: Long, updatedReviewRequest: UpdateReviewRequest, @AuthenticationPrincipal user:UserPrincipal):ResponseEntity<ReviewCommonResponse>
        = ResponseEntity.status(HttpStatus.OK).body(reviewService.setReview(reviewId,updatedReviewRequest,user.id));



    @DeleteMapping("/{reviewId}")
    fun deleteReview(@PathVariable reviewId: Long, @AuthenticationPrincipal user:UserPrincipal):ResponseEntity<Unit>
        = ResponseEntity.status(HttpStatus.NO_CONTENT).body(reviewService.deleteReview(reviewId,user.id));




}