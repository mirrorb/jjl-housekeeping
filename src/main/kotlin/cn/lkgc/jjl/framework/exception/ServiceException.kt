package cn.lkgc.jjl.framework.exception

import org.springframework.http.HttpStatus

class ServiceException(
    val code: Int = HttpStatus.INTERNAL_SERVER_ERROR.value(), override val message: String
) : RuntimeException(message) {
    constructor(message: String) : this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message)
}
