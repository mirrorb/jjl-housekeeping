package cn.lkgc.jjl.entity

import cn.lkgc.jjl.enums.UserTypeEnums
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

/**
 * 客户用户实体类
 */
@TableName("user_customer")
data class UserCustomer(
    @TableId override var username: String? = null,
    override var password: String? = null,
    var nickname: String? = null,
    var mobile: String? = null,
    var avatar: String? = null,
    var regTime: LocalDateTime? = null,
    var loginTime: LocalDateTime? = null,
    @TableField(exist = false)
    override val role: String? = UserTypeEnums.CUSTOMER.role,
    @TableField(exist = false)
    override val tableName: String? = "user_customer",
) : UserI
