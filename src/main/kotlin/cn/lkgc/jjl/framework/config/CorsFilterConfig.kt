package cn.lkgc.jjl.framework.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@EnableConfigurationProperties(CorsProperties::class)
@Configuration
class CorsFilterConfig(
    private val corsProperties: CorsProperties,
) {

    @Bean
    fun corsFilterBean(): FilterRegistrationBean<CorsFilter> {
        // 创建 CorsConfiguration 对象
        val config = CorsConfiguration()
        config.allowCredentials = true
        corsProperties.allowOrigins.forEach { config.addAllowedOrigin(it) }
//        config.addAllowedOriginPattern("*") // 设置访问源地址
        config.addAllowedHeader("*") // 设置访问源请求头
        config.addAllowedMethod("*") // 设置访问源请求方法
        // 创建 UrlBasedCorsConfigurationSource 对象
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config) // 对接口配置跨域设置
        val filter: FilterRegistrationBean<CorsFilter> = FilterRegistrationBean(CorsFilter(source))
        filter.order = Integer.MIN_VALUE
        return filter
    }
}