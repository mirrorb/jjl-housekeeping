package cn.lkgc.jjl.controller.vo.globalnews

import cn.lkgc.jjl.framework.pojo.PageParam

data class GlobalNewsAdminPageReqVO(
    val newsName: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)