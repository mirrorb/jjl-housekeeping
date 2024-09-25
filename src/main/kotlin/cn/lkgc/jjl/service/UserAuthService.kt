package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.userauth.UserAuthLoginRespVO
import cn.lkgc.jjl.controller.vo.userauth.UserAuthResetPasswordReqVO

interface UserAuthService {
    fun login(username: String, password: String, selectRole: String?): UserAuthLoginRespVO
    fun resetPassword(reqVO: UserAuthResetPasswordReqVO)
}