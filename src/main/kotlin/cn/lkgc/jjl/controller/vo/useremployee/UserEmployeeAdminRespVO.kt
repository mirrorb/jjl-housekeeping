package cn.lkgc.jjl.controller.vo.useremployee

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class UserEmployeeAdminRespVO(
    var username: String? = null,
    var password: String? = null,
    var name: String? = null,
    var mobile: String? = null,
    var avatar: String? = null,
    var gender: String? = null,
    var age: Int? = null,
    var bizPrice: Double? = null,
    var bizArea: String? = null,
    var bizMerchant: String? = null,
    var bizType: String? = null,
    var workType: String? = null,
    var picture: String? = null,
    var description: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var regTime: LocalDateTime? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var loginTime: LocalDateTime? = null,
) : Serializable
