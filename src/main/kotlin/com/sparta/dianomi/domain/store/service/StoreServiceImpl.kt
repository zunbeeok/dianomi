package com.sparta.dianomi.domain.store.service

import com.sparta.dianomi.domain.store.dto.CreateStoreDto
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import com.sparta.dianomi.domain.store.dto.UpdateStoreDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl():StoreService {
    override fun getStore(storeId: Long): StoreResponseDto? {
        TODO("entity에 접근해서 값이 있는지 확인하고 있다면 dto로 변환해서 넘겨주기")
    }

    @Transactional
    override fun createStore(createStoreDto: CreateStoreDto): StoreResponseDto {
        TODO("dto를 받아와서 entity로 변환후 save하고//더티체킹? save안써도된다//만들어진거 다시 response로 반환")
    }

    @Transactional
    override fun updateStore(storeId: Long, updateStoreDto: UpdateStoreDto): StoreResponseDto {
        TODO("StoreId값으로 entity안에 있는지 부터 확인하고 error던진다음 dto로 받은값 entity로 변환후 저장하고 다시 Response로 반환")
    }

    @Transactional
    override fun deleteStore(storeId: Long) {
        TODO("StoreId 값을 받아서 값이 있는지 확인하고 error를 던지고 삭제 반환은 없음 !")
    }
}