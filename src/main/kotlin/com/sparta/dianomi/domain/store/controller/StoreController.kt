package com.sparta.dianomi.domain.store.controller

import com.sparta.dianomi.domain.store.dto.CreateStoreDto
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import com.sparta.dianomi.domain.store.dto.UpdateStoreDto
import com.sparta.dianomi.domain.store.service.StoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/stores")
@RestController
class StoreController (
    private val storeService: StoreService
){
    @GetMapping("/{storeId}")
    fun getStore(
        @PathVariable storeId:Long
    ):ResponseEntity<StoreResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.getStore(storeId))
    }
    //가게 단건 조회
    @GetMapping
    fun getStoreList():ResponseEntity<List<StoreResponseDto>>{
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(storeService.gerStoreList())
    }

    @PostMapping
    fun createStore(
        @RequestBody createStoreDto: CreateStoreDto
    ):ResponseEntity<StoreResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(storeService.createStore(createStoreDto))
    }
    //가게 정보 생성

    @PutMapping("/{storeId}")
    fun updateStore(
        @PathVariable storeId: Long,
        @RequestBody updateStoreDto: UpdateStoreDto
    ):ResponseEntity<StoreResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.updateStore(storeId,updateStoreDto))
    }
    //가게 정보 수정
    @DeleteMapping("/{storeId}")
    fun deleteStore(
        @PathVariable storeId:Long
    ):ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
    //가게 정보 삭제
}