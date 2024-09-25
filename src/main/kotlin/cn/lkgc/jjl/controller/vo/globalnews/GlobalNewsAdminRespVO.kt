package cn.lkgc.jjl.controller.vo.globalnews

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class GlobalNewsAdminRespVO(
    var newsName: String? = null,
    var picture: String? = null,
    var description: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var createTime: LocalDateTime? = null,
) : Serializable
