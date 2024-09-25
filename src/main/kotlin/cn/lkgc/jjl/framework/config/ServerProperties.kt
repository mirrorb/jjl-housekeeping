package cn.lkgc.jjl.framework.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "server")
class ServerProperties {
    lateinit var host: String
    var port: String? = null
}