package com.sparta.dianomi.domain.store.repository

import com.sparta.dianomi.domain.store.model.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository:JpaRepository<Store,Long> {
    fun findStoreIdsByUserId(userId: Long): List<Store>

}