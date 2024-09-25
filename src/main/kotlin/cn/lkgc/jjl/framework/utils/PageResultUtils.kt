package cn.lkgc.jjl.framework.utils

import cn.lkgc.jjl.framework.pojo.PageResult
import com.baomidou.mybatisplus.extension.plugins.pagination.Page

object PageResultUtils {
    fun <T> empty(): PageResult<T> {
        return PageResult(emptyList(), 0L)
    }

    fun <T> empty(total: Long): PageResult<T> {
        return PageResult(emptyList(), total)
    }

    fun <T> from(page: Page<T>): PageResult<T> {
        return PageResult(page.records, page.total)
    }
}