package cn.lkgc.jjl.controller.vo.usercustomer

import cn.lkgc.jjl.framework.pojo.PageParam

data class UserCustomerAdminPageReqVO(
    var username: String? = null,
    var nickname: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)