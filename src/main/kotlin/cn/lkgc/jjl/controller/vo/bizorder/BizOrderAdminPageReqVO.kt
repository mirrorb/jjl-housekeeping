package cn.lkgc.jjl.controller.vo.bizorder

import cn.lkgc.jjl.framework.pojo.PageParam

data class BizOrderAdminPageReqVO(
    var id: Int? = null,
    var customer: String? = null,
    var customerMobile: String? = null,
    var employee: String? = null,
    var employeeMobile: String? = null,
    var status: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)
