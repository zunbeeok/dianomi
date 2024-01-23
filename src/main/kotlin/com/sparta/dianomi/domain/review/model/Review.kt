package com.sparta.dianomi.domain.review.model

import com.sparta.dianomi.common.model.BaseTimeEntity
//import com.sparta.dianomi.domain.review.dto.UpdateReviewRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Review (
    @Column(name = "rating")
    var rating:Long,

    @Column(name = "comment")
    var comment:String,

    @Column(name = "menu_id")
    val menuId :Long,

    @Column(name = "store_id")
    val storeId: Long,

    @Column(name = "user_id")
    val userId:Long,
):BaseTimeEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

//    fun updateRatingAndComment(updateReview: UpdateReviewRequest){
//        this.rating = updateReview.rating;
//        this.comment = updateReview.comment;
//    }
}