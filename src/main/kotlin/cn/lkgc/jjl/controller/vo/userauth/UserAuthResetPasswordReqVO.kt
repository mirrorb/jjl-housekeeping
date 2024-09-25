package cn.lkgc.jjl.controller.vo.userauth

data class UserAuthResetPasswordReqVO(
    val oldPassword: String,
    val newPassword: String
)
