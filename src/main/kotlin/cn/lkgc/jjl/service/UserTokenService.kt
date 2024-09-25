package cn.lkgc.jjl.service

import cn.lkgc.jjl.entity.UserI
import cn.lkgc.jjl.entity.UserToken

interface UserTokenService {
    fun getValidByToken(token: String?): UserToken?
    fun generateToken(user: UserI): String
    fun refreshExpireTime(id: Int)
}