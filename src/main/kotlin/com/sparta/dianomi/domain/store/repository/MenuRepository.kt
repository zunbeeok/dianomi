package com.sparta.dianomi.domain.store.repository

import com.sparta.dianomi.domain.store.model.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long>{
    fun findAllByStoreId(storeId: Long): List<Menu>
}