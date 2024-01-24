package com.sparta.dianomi.domain.review.model

import com.sparta.dianomi.common.exception.InvalidCredentialException
import com.sparta.dianomi.common.model.BaseTimeEntity
import com.sparta.dianomi.domain.review.dto.UpdateReviewRequest
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

//    @Column(name = "menu_id")
//    val menuId :Long,

    @Column(name = "store_id")
    val storeId: Long,
    //storeName을 없이 일단 가져오는데 storeName 가져와야 할수도?

    @Column(name = "user_id")
    val userId:Long,


):BaseTimeEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    fun updateRatingAndComment(updateReview: UpdateReviewRequest){
        this.rating = updateReview.rating;
        this.comment = updateReview.description;
    }

    fun compareMemberId(userId:Long){
        if(this.userId != userId) throw InvalidCredentialException("작성자가 아닙니다.")
    }
}