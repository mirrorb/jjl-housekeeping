package cn.lkgc.jjl.controller.vo.usercustomer

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class UserCustomerAdminRespVO(
    var username: String? = null,
    var password: String? = null,
    var nickname: String? = null,
    var mobile: String? = null,
    var avatar: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var regTime: LocalDateTime? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var loginTime: LocalDateTime? = null,
) : Serializable
