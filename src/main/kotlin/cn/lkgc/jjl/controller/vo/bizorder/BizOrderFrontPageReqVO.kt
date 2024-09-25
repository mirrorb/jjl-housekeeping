package cn.lkgc.jjl.controller.vo.bizorder

import cn.lkgc.jjl.framework.pojo.PageParam

data class BizOrderFrontPageReqVO(
    val status: String,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
) : PageParam(current, pageSize)
