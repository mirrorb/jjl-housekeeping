package cn.lkgc.jjl.controller.vo.bizorder

import java.io.Serializable

data class BizOrderReviewReqVO(
    val id: Int,
    val status: String,
    val reviewMsg: String,
) : Serializable