package cn.lkgc.jjl.controller.vo.bizorder

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class BizOrderAdminRespVO(
    var id: Int? = null,
    var customer: String? = null,
    var customerMobile: String? = null,
    var employee: String? = null,
    var employeeMobile: String? = null,
    var appointmentAddress: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var appointmentTime: LocalDateTime? = null,
    var appointmentDuration: Double? = null,
    var amount: Double? = null,
    var status: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDateTime? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var reviewTime: LocalDateTime? = null,
    var reviewMsg: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var finalizeTime: LocalDateTime? = null,
    var rate: Double? = null,
) : Serializable
