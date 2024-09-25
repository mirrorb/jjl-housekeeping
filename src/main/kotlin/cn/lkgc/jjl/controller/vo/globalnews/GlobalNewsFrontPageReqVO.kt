package cn.lkgc.jjl.controller.vo.globalnews

import cn.lkgc.jjl.framework.pojo.PageParam
import org.springframework.stereotype.Component

@Component
data class GlobalNewsFrontPageReqVO(
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)
