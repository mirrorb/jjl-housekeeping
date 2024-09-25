package cn.lkgc.jjl.controller.vo.globalcarousel

import java.io.Serializable

data class GlobalCarouselAdminCreateReqVO(
    val name: String? = null,
    val image: String? = null,
    val sort: String? = null,
): Serializable
