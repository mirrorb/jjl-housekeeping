package cn.lkgc.jjl.framework.pojo

import lombok.Data

@Data
class CommonResult<T>(
    val code: Int,
    val msg: String,
    val data: T
) {
    companion object {
        fun <T> success(data: T): CommonResult<T> {
            return CommonResult(200, "成功", data)
        }

        fun <T> error(code: Int, msg: String): CommonResult<T?> {
            return CommonResult(code, msg, null)
        }
    }
}
