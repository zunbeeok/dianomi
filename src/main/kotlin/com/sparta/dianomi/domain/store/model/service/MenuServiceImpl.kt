package com.sparta.dianomi.domain.store.model.service

import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.store.model.dto.MenuCreateDto
import com.sparta.dianomi.domain.store.model.dto.MenuDto
import com.sparta.dianomi.domain.store.model.dto.MenuUpdateDto
import com.sparta.dianomi.domain.store.model.Menu
import com.sparta.dianomi.domain.store.model.repository.MenuRepository
import com.sparta.dianomi.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuServiceImpl(
    private val storeRepository: StoreRepository,
    private val menuRepository: MenuRepository
): MenuService {

    override fun getAllMenus(storeId: Long): List<MenuDto> {
        val menus = menuRepository.findAllByStoreId(storeId)
        return menus.map { MenuDto.from(it) }
    }

    override fun getMenu(storeId: Long, id: Long): MenuDto {
        val menu = menuRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Menu", id)
        return MenuDto.from(menu)
    }

    override fun createMenu(storeId: Long, menuCreateDto: MenuCreateDto): MenuDto {
        val newMenu = Menu(
            name = menuCreateDto.name,
            price = menuCreateDto.price,
            description = menuCreateDto.description,
            storeId = storeId
        )
        menuRepository.save(newMenu)
        return MenuDto.from(newMenu)
    }

    override fun deleteMenu(storeId: Long, id: Long) {
        val menu = menuRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Menu", id)
        menuRepository.delete(menu)
    }

    override fun updateMenu(storeId: Long, id: Long, menuUpdateDto: MenuUpdateDto): MenuDto {
        val menu = menuRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Menu", id)
        menu.name = menuUpdateDto.name
        menu.price = menuUpdateDto.price
        menu.description = menuUpdateDto.description
        menuRepository.save(menu)
        return MenuDto.from(menu)
    }
}