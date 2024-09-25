package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 系统轮播图实体类
 */
@TableName("global_carousel")
data class GlobalCarousel(
    @TableId var name: String? = null,
    var image: String? = null,
    var sort: Int? = null,
    var createTime: LocalDateTime? = null,
) : Serializable
