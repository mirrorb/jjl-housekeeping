package cn.lkgc.jjl.controller.vo.usercustomer

import java.io.Serializable

data class UserCustomerSelfUpdateReqVO(
    var username: String? = null,
    var nickname: String? = null,
    var mobile: String? = null,
    var avatar: String? = null,
): Serializable
