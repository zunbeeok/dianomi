package com.sparta.dianomi.domain.store.model.service

import com.sparta.dianomi.domain.store.model.dto.MenuCreateDto
import com.sparta.dianomi.domain.store.model.dto.MenuDto
import com.sparta.dianomi.domain.store.model.dto.MenuUpdateDto

interface MenuService {
    fun createMenu(storeId: Long, menuCreateDto: MenuCreateDto): MenuDto
    fun updateMenu(storeId: Long, id: Long, menuUpdateDto: MenuUpdateDto): MenuDto
    fun getMenu(storeId: Long, id: Long): MenuDto
    fun getAllMenus(storeId: Long): List<MenuDto>
    fun deleteMenu(storeId: Long, id: Long)
}