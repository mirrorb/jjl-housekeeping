package cn.lkgc.jjl.controller.vo.usercustomer

import java.io.Serializable

data class UserCustomerFrontRespVO(
    var username: String? = null,
    var nickname: String? = null,
    var mobile: String? = null,
    var gender: String? = null,
    var avatar: String? = null,
) : Serializable
