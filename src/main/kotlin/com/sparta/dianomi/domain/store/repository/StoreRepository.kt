package com.sparta.dianomi.domain.store.repository

import com.sparta.dianomi.domain.store.model.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository:JpaRepository<Store,Long> {

    //fun findByStoreIdAndMemberId(id:Long,memberId:Long)
    //내장함수 아니고 이름추천 직관적으루
}