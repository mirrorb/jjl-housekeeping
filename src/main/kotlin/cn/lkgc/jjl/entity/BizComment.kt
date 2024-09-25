package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 评论实体类
 */
@TableName("biz_comment")
data class BizComment(
    @TableId var id: Int? = null,
    var target: String? = null,
    var targetType: String? = null,
    var username: String? = null,
    var role: String? = null,
    var text: String? = null,
    var parentId: Int? = null,
    var createTime: LocalDateTime? = null,
) : Serializable
