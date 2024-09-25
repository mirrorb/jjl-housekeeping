package cn.lkgc.jjl.controller.vo.usercustomer

import java.io.Serializable

data class UserCustomerRegReqVO(
    val username: String,
    val password: String,
    val nickname: String,
    val mobile: String,
    val avatar: String,
) : Serializable
