package cn.lkgc.jjl.framework.handler

import cn.lkgc.jjl.framework.exception.ServiceException
import cn.lkgc.jjl.framework.pojo.CommonResult
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    private final val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ServiceException::class)
    fun handleCustomException(ex: ServiceException, request: WebRequest): CommonResult<Any?> {
        log.error(ex.message, ex)
        return CommonResult.error(
            ex.code,
            ex.message
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoResourceFoundException(ex: NoHandlerFoundException, request: WebRequest): CommonResult<Any?> {
        log.error(ex.message, ex)
        return CommonResult.error(
            HttpStatus.NOT_FOUND.value(),
            "资源未找到"
        )
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(ex: NoResourceFoundException, request: WebRequest): CommonResult<Any?> {
        log.error(ex.message, ex)
        return CommonResult.error(
            HttpStatus.NOT_FOUND.value(),
            "静态资源未找到"
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleMethodNotSupportedException(
        ex: HttpRequestMethodNotSupportedException,
        request: WebRequest
    ): CommonResult<Any?> {
        log.error(ex.message, ex)
        return CommonResult.error(
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            "请求方法不支持"
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception, request: WebRequest): CommonResult<Any?> {
        log.error(ex.message, ex)
        return CommonResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务异常")
    }

}