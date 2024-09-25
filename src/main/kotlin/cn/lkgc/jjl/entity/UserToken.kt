package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 用户令牌实体类
 */
@TableName("user_token")
data class UserToken(
    @TableId val id: Int? = null,
    val tableName: String? = null,
    val username: String? = null,
    val role: String? = null,
    val token: String? = null,
    val genTime: LocalDateTime? = null,
    val expiredTime: LocalDateTime? = null,
) : Serializable
