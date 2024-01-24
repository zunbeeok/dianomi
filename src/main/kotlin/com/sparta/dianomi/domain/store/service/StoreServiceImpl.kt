package com.sparta.dianomi.domain.store.service

import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.store.dto.CreateStoreDto
import com.sparta.dianomi.domain.store.dto.StoreResponseDto
import com.sparta.dianomi.domain.store.dto.UpdateStoreDto
import com.sparta.dianomi.domain.store.model.Store
import com.sparta.dianomi.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(
    private val storeRepository: StoreRepository,
):StoreService {
    override fun getStore(storeId: Long): StoreResponseDto {
        val findStore = storeRepository.findByIdOrNull(storeId)?: throw ModelNotFoundException("store", storeId)
        //데이터를 찾지 못함
        return findStore.toResponse();
        //dto를 제외하고 클래스를 호출할 생각 X 하면 의존성 증가.
    }
    //접근하는 user가 상점의 특정정보를 들고있어야 대조해보고



    override fun getStoreList(): List<StoreResponseDto> {
        return storeRepository.findAll().map{it.toResponse()}
    }

    @Transactional
    override fun createStore(createStoreDto: CreateStoreDto, userId: Long): StoreResponseDto {
        return storeRepository.save(
            Store(
                name = createStoreDto.name,
                address = createStoreDto.address,
                businessNum = createStoreDto.businessNum,
                description = createStoreDto.description,
                userId = userId

            )
        ).toResponse()
    }//상호명 같은거 처리해줘야하는지?

    @Transactional
    override fun updateStore(storeId: Long, updateStoreDto: UpdateStoreDto, userId: Long): StoreResponseDto {
        _checkOwner(storeId, userId)
       val findStore = storeRepository.findByIdOrNull(storeId)?: throw ModelNotFoundException("store", storeId)
        //수정하는 user와 store를 해서
        extracted(findStore, updateStoreDto)
        // 함수로 바꾸면 entity안에 ?!

        return storeRepository.save(findStore).toResponse()
    }

    private fun extracted(
        findStore: Store,
        updateStoreDto: UpdateStoreDto
    ) {
        findStore.name = updateStoreDto.name
        findStore.address = updateStoreDto.address
        findStore.businessNum = updateStoreDto.businessNum
        findStore.description = updateStoreDto.description
    }

    @Transactional
    override fun deleteStore(storeId: Long, userId: Long) {
        _checkOwner(storeId, userId)
        val findStore = storeRepository.findByIdOrNull(storeId)?: throw ModelNotFoundException("store", storeId)
        storeRepository.delete(findStore)
    }

    private fun _checkOwner(storeId: Long,userId: Long) {
        val store : Store =storeRepository.findByIdOrNull(storeId) ?: throw Exception("Store not found");
        if (store.userId != userId) {
            throw Exception("User does not have permission to modify this store")
        }


    }
}