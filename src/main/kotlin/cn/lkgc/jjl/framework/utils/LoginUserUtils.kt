package cn.lkgc.jjl.framework.utils

import cn.lkgc.jjl.framework.pojo.LoginUser
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

object LoginUserUtils {

    val loginUser: LoginUser?
        get() {
            val attributes = RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes
            val session = attributes?.request?.session
            val username = session?.getAttribute("username") as? String
            val tableName = session?.getAttribute("tableName") as? String
            val role = session?.getAttribute("role") as? String
            if (username == null || tableName == null || role == null) {
                return null
            }
            return LoginUser(username, tableName, role)
        }

}
