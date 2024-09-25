package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.userauth.UserAuthLoginReqVO
import cn.lkgc.jjl.controller.vo.userauth.UserAuthLoginRespVO
import cn.lkgc.jjl.controller.vo.userauth.UserAuthResetPasswordReqVO
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.service.UserAuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 用户登录控制器
 */
@RequestMapping("/auth")
@RestController
class UserAuthController(
    private val userAuthService: UserAuthService
) {

    @Permit
    @PostMapping("/login")
    fun login(@RequestBody reqVO: UserAuthLoginReqVO): CommonResult<UserAuthLoginRespVO> {
        return CommonResult.success(userAuthService.login(reqVO.username, reqVO.password, reqVO.selectRole))
    }

    @PostMapping("/reset-password")
    fun resetPassword(@RequestBody reqVO: UserAuthResetPasswordReqVO): CommonResult<Boolean> {
        userAuthService.resetPassword(reqVO)
        return CommonResult.success(true)
    }

}