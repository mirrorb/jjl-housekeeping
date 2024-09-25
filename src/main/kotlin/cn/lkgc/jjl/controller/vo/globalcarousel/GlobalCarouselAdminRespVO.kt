package cn.lkgc.jjl.controller.vo.globalcarousel

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class GlobalCarouselAdminRespVO(
    var name: String? = null,
    var image: String? = null,
    var sort: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var createTime: LocalDateTime? = null,
) : Serializable
