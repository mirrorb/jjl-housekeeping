package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 系统公告实体类
 */
@TableName("global_news")
data class GlobalNews(
    @TableId var newsName: String? = null,
    var picture: String? = null,
    var description: String? = null,
    var createTime: LocalDateTime? = null,
) : Serializable 
