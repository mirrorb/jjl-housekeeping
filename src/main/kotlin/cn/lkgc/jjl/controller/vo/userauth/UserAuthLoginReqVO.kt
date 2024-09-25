package cn.lkgc.jjl.controller.vo.userauth

import java.io.Serializable


data class UserAuthLoginReqVO(
    val username: String,
    val password: String,
    val selectRole: String? = null,
): Serializable
