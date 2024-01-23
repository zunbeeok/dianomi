package com.sparta.dianomi.domain.store.service

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
        val findStore = storeRepository.findByIdOrNull(storeId)?: throw Exception("store not found");
        return findStore.toResponse();
        //dto를 제외하고 클래스를 호출할 생각 X 하면 의존성 증가.
    }

//    override fun getStore(storeId: Long): StoreResponseDto =
//        storeRepository.findByIdOrNull(storeId).let {
//            it ?: throw Exception()
//        }.toResponse()
//    //stream 기법 물흐르듯이


    override fun gerStoreList(): List<StoreResponseDto> {
        return storeRepository.findAll().map{it.toResponse()}
    }

    @Transactional
    override fun createStore(createStoreDto: CreateStoreDto): StoreResponseDto {
        return storeRepository.save(
            Store(
                name = createStoreDto.name,
                address = createStoreDto.address,
                businessNum = createStoreDto.businessNum,
                description = createStoreDto.description
            )
        ).toResponse()
    }//상호명 같은거 처리해줘야하는지?

    @Transactional
    override fun updateStore(storeId: Long, updateStoreDto: UpdateStoreDto): StoreResponseDto {
       val findStore = storeRepository.findByIdOrNull(storeId)?: throw Exception("not found store")

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
    override fun deleteStore(storeId: Long) {
        val findStore = storeRepository.findByIdOrNull(storeId)?: throw Exception("store not found")
        storeRepository.delete(findStore)
    }
}