package cn.lkgc.jjl.controller.vo.globalgraph

import java.io.Serializable

data class GlobalGraphEmployeeRespVO(
    var username: String? = null,
    var detailCount: Int? = null,
    var niceCount: Int? = null,
    var starCount: Int? = null,
    var orderCount: Int? = null,
) : Serializable
