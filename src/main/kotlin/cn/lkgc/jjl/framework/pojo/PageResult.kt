package cn.lkgc.jjl.framework.pojo

class PageResult<T>(
    var list: List<T>,
    val total: Long
)
