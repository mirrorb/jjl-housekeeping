package cn.lkgc.jjl.controller.vo.biztype

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class BizTypeAdminRespVO(
    var typeName: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var createTime: LocalDateTime? = null,
) : Serializable