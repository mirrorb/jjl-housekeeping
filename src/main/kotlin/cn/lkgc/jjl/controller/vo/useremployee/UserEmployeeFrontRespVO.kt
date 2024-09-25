package cn.lkgc.jjl.controller.vo.useremployee

import java.io.Serializable

data class UserEmployeeFrontRespVO(
    var username: String? = null,
    var name: String? = null,
    var mobile: String? = null,
    var gender: String? = null,
    var avatar: String? = null,
    var age: Int? = null,
    var bizPrice: Double? = null,
    var bizArea: String? = null,
    var bizMerchant: String? = null,
    var bizType: String? = null,
    var workType: String? = null,
    var picture: String? = null,
    var description: String? = null,
    var star: Boolean? = null,
    var starCount: Int? = null,
    var nice: Boolean? = null,
    var niceCount: Int? = null
) : Serializable
