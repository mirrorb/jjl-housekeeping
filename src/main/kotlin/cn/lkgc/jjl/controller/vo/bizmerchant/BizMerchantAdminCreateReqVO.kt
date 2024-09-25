package cn.lkgc.jjl.controller.vo.bizmerchant

import java.io.Serializable

data class BizMerchantAdminCreateReqVO(
    val merchantName: String? = null,
    val address: String? = null,
    val telephone: String? = null,
    val picture: String? = null,
    val description: String? = null,
): Serializable
