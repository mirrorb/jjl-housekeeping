package cn.lkgc.jjl.controller.vo.userauth

import cn.lkgc.jjl.entity.UserI
import java.io.Serializable

data class UserAuthLoginRespVO(
    val token: String,
    val role: String,
    val user: UserI,
) : Serializable
