package cn.lkgc.jjl.framework.config

import cn.lkgc.jjl.framework.interceptor.AuthorizationInterceptor
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@EnableConfigurationProperties(WebMvcProperties::class)
@Configuration
class WebMvcConfig(
    private val authorizationInterceptor: AuthorizationInterceptor,
    private val resourcesProperties: ResourcesProperties
) : WebMvcConfigurationSupport() {
    // 拦截器设置
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**")
        super.addInterceptors(registry)
    }
    // 静态资源设置
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler(*resourcesProperties.pathPatterns)
            .addResourceLocations(*resourcesProperties.locations)
        super.addResourceHandlers(registry)
    }
}