package cn.lkgc.jjl.controller.vo.globalcarousel

import java.io.Serializable

data class GlobalCarouselAdminUpdateReqVO(
    val name: String? = null,
    val image: String? = null,
    val sort: String? = null,
): Serializable
