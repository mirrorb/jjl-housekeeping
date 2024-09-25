package cn.lkgc.jjl.framework.interceptor

import cn.lkgc.jjl.entity.UserToken
import cn.lkgc.jjl.enums.ErrorCodeEnums
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.service.UserTokenService
import com.alibaba.fastjson2.JSONObject
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthorizationInterceptor(
    private val userTokenService: UserTokenService
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (request.method.equals(RequestMethod.OPTIONS.name)) {
            response.status = HttpStatus.OK.value()
            return false
        }
        // 不是HandlerMethod的直接放过
        val annotation: Permit?
        if (handler is HandlerMethod) {
            annotation = handler.getMethodAnnotation(Permit::class.java)
        } else {
            return true
        }
        // 从header中获取token
        val token: String? = request.getHeader("Authorization")
        var tokenEntity: UserToken? = null
        if (!token.isNullOrEmpty()) {
            tokenEntity = userTokenService.getValidByToken(token)
        }
        if (tokenEntity != null) {
            userTokenService.refreshExpireTime(tokenEntity.id!!)
            request.session.setAttribute("role", tokenEntity.role)
            request.session.setAttribute("tableName", tokenEntity.tableName)
            request.session.setAttribute("username", tokenEntity.username)
            return true
        }
        // 有Permit的直接放过
        if (annotation != null) {
            return true
        }
        // 直接响应未登录
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json; charset=utf-8"
        response.writer?.write(JSONObject.toJSONString(CommonResult.error<Any>(ErrorCodeEnums.NEED_AUTHORIZATION.value, "用户未登录")))
        return false
    }

}