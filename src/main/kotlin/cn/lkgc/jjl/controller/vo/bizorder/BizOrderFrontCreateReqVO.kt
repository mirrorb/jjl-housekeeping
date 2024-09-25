package cn.lkgc.jjl.controller.vo.bizorder

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class BizOrderFrontCreateReqVO(
    val employee: String,
    val appointmentAddress: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val appointmentTime: LocalDateTime,
    val appointmentDuration: Double,
) : Serializable
