package cn.lkgc.jjl.framework.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cors")
class CorsProperties {
    lateinit var allowOrigins: Array<String>
}