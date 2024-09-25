package cn.lkgc.jjl.controller.vo.globalnews

import java.io.Serializable

data class GlobalNewsAdminCreateReqVO(
    val newsName: String? = null,
    val picture: String? = null,
    val description: String? = null,
): Serializable
