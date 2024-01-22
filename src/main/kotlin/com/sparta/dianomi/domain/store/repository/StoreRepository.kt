package com.sparta.dianomi.domain.store.repository

import com.sparta.dianomi.domain.store.model.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository:JpaRepository<StoreEntity,Long> {
}