package cn.lkgc.jjl.controller.vo.useremployee

import cn.lkgc.jjl.framework.pojo.PageParam

data class UserEmployeeFrontPageReqVO(
    val employee: String? = null,
    val name: String? = null,
    val star: Boolean? = null,
    val nice: Boolean? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)
