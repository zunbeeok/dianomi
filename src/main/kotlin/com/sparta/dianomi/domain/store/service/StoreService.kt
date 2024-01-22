package com.sparta.dianomi.domain.store.service

import com.sparta.dianomi.domain.store.dto.CreateStoreDto
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import com.sparta.dianomi.domain.store.dto.UpdateStoreDto

interface StoreService {
    fun getStore(storeId:Long):StoreResponseDto?
    //가게정보조회
    fun createStore(createStoreDto: CreateStoreDto):StoreResponseDto
    //신규상점입점
    fun updateStore(storeId: Long,updateStoreDto: UpdateStoreDto):StoreResponseDto
    //상점정보수정
    fun deleteStore(storeId: Long)
    //상점정보삭제

}
