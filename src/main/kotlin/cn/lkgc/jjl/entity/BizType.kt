package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 服务类别实体类
 */
@TableName("biz_type")
data class BizType(
    @TableId var typeName: String? = null,
    var createTime: LocalDateTime? = null,
) : Serializable
