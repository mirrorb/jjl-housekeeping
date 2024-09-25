package cn.lkgc.jjl.framework.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MybatisPlusConfig {

    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val mybatisPlusInterceptor = MybatisPlusInterceptor()
        mybatisPlusInterceptor.addInnerInterceptor(PaginationInnerInterceptor(DbType.POSTGRE_SQL)) // 分页插件
        return mybatisPlusInterceptor
    }

}