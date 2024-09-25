package cn.lkgc.jjl.controller.vo.bizarea

import cn.lkgc.jjl.framework.pojo.PageParam

data class BizAreaAdminPageReqVO(
    val areaName: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)