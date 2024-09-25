package cn.lkgc.jjl.controller.vo.biztype

import java.io.Serializable

data class BizTypeAdminUpdateReqVO(
    val typeName: String? = null,
    val image: String? = null,
    val sort: String? = null,
): Serializable
