package com.sparta.dianomi.domain.store.controller

import com.sparta.dianomi.domain.store.dto.CreateStoreDto
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import com.sparta.dianomi.domain.store.dto.UpdateStoreDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/store")
@RestController
class StoreController {
    @GetMapping("/{storeId}")
    fun getStore(
        @PathVariable storeId:Long
    ):ResponseEntity<StoreResponseDto>{
        TODO()
    }

    @PostMapping
    fun createStore(
        @RequestBody createStoreDto: CreateStoreDto
    ):ResponseEntity<StoreResponseDto>{
        TODO()
    }

    @PutMapping("/{storeId}")
    fun updateStore(
        @PathVariable storeId: Long,
        @RequestBody updateStoreDto: UpdateStoreDto
    ):ResponseEntity<StoreResponseDto>{
        TODO()
    }

    @DeleteMapping("/{storeId}")
    fun deleteStore(
        @PathVariable storeId:Long
    ):ResponseEntity<Unit>{
        TODO()
    }
}