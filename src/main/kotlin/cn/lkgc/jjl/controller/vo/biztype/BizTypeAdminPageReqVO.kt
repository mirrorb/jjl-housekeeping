package cn.lkgc.jjl.controller.vo.biztype

import cn.lkgc.jjl.framework.pojo.PageParam

data class BizTypeAdminPageReqVO(
    val typeName: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)