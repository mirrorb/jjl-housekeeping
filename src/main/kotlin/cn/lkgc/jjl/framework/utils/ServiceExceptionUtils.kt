package cn.lkgc.jjl.framework.utils

import cn.lkgc.jjl.framework.exception.ServiceException
import org.springframework.http.HttpStatus

object ServiceExceptionUtils {

    fun exception(code: Int, message: String): ServiceException = ServiceException(code, message)

    fun exception(message: String): ServiceException =
        ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), message)

}