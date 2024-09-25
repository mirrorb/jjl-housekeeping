package cn.lkgc.jjl.enums

import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception

enum class StoreTypeEnums(
    val value: String
) {
    STAR("收藏"), NICE("点赞"), NICE_STAR("点赞收藏");

    companion object {
        fun fromValue(value: String): StoreTypeEnums {
            return entries.find { it.value == value } ?: throw exception("错误的操作")
        }
    }
}