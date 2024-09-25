package cn.lkgc.jjl.controller.vo.globalcarousel

import cn.lkgc.jjl.framework.pojo.PageParam

data class GlobalCarouselAdminPageReqVO(
    val name: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)