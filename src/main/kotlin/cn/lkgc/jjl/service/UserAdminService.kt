package cn.lkgc.jjl.service

import cn.lkgc.jjl.entity.UserAdmin

interface UserAdminService {
    fun getByUsername(username: String): UserAdmin?
    fun updateLoginTime(user: UserAdmin)
    fun update(doo: UserAdmin)
}