package cn.lkgc.jjl.controller.vo.bizarea

import java.io.Serializable

data class BizAreaAdminUpdateReqVO(
    val areaName: String? = null,
    val image: String? = null,
    val sort: String? = null,
): Serializable
