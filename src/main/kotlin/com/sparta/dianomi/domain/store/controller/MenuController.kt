package com.sparta.dianomi.domain.store.controller


import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.store.dto.MenuCreateDto
import com.sparta.dianomi.domain.store.dto.MenuDto
import com.sparta.dianomi.domain.store.dto.MenuUpdateDto
import com.sparta.dianomi.domain.store.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Menu", description = "메뉴 API")
@RequestMapping("/stores/{storeId}/menus")
class MenuController(private val menuService: MenuService) {

    @Operation(summary = "메뉴 정보 업데이트", description = "주어진 메뉴 ID에 해당하는 메뉴의 정보를 업데이트합니다.")
    @PutMapping("/{menuId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateMenu(
        @AuthenticationPrincipal user: UserPrincipal,
        @Parameter(description = "스토어 ID") @PathVariable storeId: Long,
        @Parameter(description = "메뉴 ID") @PathVariable menuId: Long,
        @Parameter(description = "업데이트할 메뉴 정보") @RequestBody menuUpdateDTO: MenuUpdateDto
    ): ResponseEntity<out Any> {
        menuService.updateMenu(storeId, menuId, menuUpdateDTO, user.id)
        return ResponseEntity.ok(menuUpdateDTO)
    }

    @Operation(summary = "새 메뉴 생성", description = "새로운 메뉴를 생성합니다.")
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createMenu(
        @AuthenticationPrincipal user: UserPrincipal,
        @Parameter(description = "스토어 ID") @PathVariable storeId: Long,
        @Parameter(description = "생성할 메뉴 정보") @RequestBody menuCreateDto: MenuCreateDto
    ): ResponseEntity<MenuDto> {
        val menuDto = menuService.createMenu(storeId, menuCreateDto, user.id)
        return ResponseEntity.status(HttpStatus.CREATED).body(menuDto)
    }

    @Operation(summary = "메뉴 삭제", description = "주어진 메뉴 ID에 해당하는 메뉴를 삭제합니다.")
    @DeleteMapping("/{menuId}", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun deleteMenu(
        @AuthenticationPrincipal user: UserPrincipal,
        @Parameter(description = "스토어 ID") @PathVariable storeId: Long,
        @Parameter(description = "메뉴 ID") @PathVariable menuId: Long
    ): ResponseEntity<out Any> {
        menuService.deleteMenu(storeId, menuId,user.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @GetMapping("/{menuId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "특정 메뉴 조회", description = "주어진 메뉴 ID에 해당하는 메뉴를 조회합니다.")
    fun getMenu(
        @Parameter(description = "스토어 ID") @PathVariable storeId: Long,
        @Parameter(description = "메뉴 ID") @PathVariable menuId: Long
    ): ResponseEntity<out Any> {
        val menu = menuService.getMenu(storeId, menuId)
        return ResponseEntity.ok(menu)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "모든 메뉴 조회", description = "모든 메뉴를 조회합니다.")
    fun getMenus(
        @Parameter(description = "스토어 ID") @PathVariable storeId: Long
    ): ResponseEntity<out Any> {
        val menus = menuService.getAllMenus(storeId)
        return if (menus.isNotEmpty()) {
            ResponseEntity.ok(menus)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("message" to "메뉴가 존재하지 않습니다."))
        }
    }
}