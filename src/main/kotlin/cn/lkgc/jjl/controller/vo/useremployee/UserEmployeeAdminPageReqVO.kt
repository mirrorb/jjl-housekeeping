package cn.lkgc.jjl.controller.vo.useremployee

import cn.lkgc.jjl.framework.pojo.PageParam

data class UserEmployeeAdminPageReqVO(
    val username: String? = null,
    val name: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)