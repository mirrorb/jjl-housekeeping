package cn.lkgc.jjl.controller.vo.useremployee

import java.io.Serializable

data class UserEmployeeFrontRegReqVO(
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val mobile: String? = null,
    val avatar: String? = null,
    val gender: String? = null,
    val age: Int? = null,
    val bizPrice: Double? = null,
    val bizArea: String? = null,
    val bizMerchant: String? = null,
    val bizType: String? = null,
    val workType: String? = null,
) : Serializable
