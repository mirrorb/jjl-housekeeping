package cn.lkgc.jjl.framework.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "resources")
class ResourcesProperties {
    lateinit var pathPatterns: Array<String>
    lateinit var locations: Array<String>
    lateinit var avatarPath: String
    lateinit var carouselPath: String
    lateinit var editorImagePath: String
    lateinit var editorVideoPath: String
    lateinit var picturePath: String
}