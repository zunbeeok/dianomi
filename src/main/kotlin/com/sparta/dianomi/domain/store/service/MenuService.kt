package com.sparta.dianomi.domain.store.service

import com.sparta.dianomi.domain.store.dto.MenuCreateDto
import com.sparta.dianomi.domain.store.dto.MenuDto
import com.sparta.dianomi.domain.store.dto.MenuUpdateDto

interface MenuService {
    fun createMenu(storeId: Long, menuCreateDto: MenuCreateDto, userId: Long): MenuDto
    fun updateMenu(storeId: Long, id: Long, menuUpdateDto: MenuUpdateDto, userId: Long): MenuDto
    fun getMenu(storeId: Long, id: Long): MenuDto
    fun getAllMenus(storeId: Long): List<MenuDto>
    fun deleteMenu(storeId: Long, id: Long, userId: Long)
}