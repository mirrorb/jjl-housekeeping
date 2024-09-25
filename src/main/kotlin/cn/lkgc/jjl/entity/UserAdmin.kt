package cn.lkgc.jjl.entity

import cn.lkgc.jjl.enums.UserTypeEnums
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

/**
 * 管理员用户实体类
 */
@TableName("user_admin")
data class UserAdmin(
    @TableId override val username: String? = null,
    override var password: String? = null,
    val avatar: String? = null,
    var loginTime: LocalDateTime? = null,
    @TableField(exist = false)
    override val role: String? = UserTypeEnums.ADMIN.role,
    @TableField(exist = false)
    override val tableName: String? = "user_admin",
) : UserI
