package cn.lkgc.jjl.entity

import cn.lkgc.jjl.enums.UserTypeEnums
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

/**
 * 雇员用户实体类
 */
@TableName("user_employee")
data class UserEmployee(
    @TableId override var username: String? = null,
    override var password: String? = null,
    var name: String? = null,
    var mobile: String? = null,
    var avatar: String? = null,
    var gender: String? = null,
    var age: Int? = null,
    var bizPrice: Double? = null,
    var bizArea: String? = null,
    var bizMerchant: String? = null,
    var bizType: String? = null,
    var workType: String? = null,
    var picture: String? = null,
    var description: String? = null,
    var regTime: LocalDateTime? = null,
    var loginTime: LocalDateTime? = null,
    var detailCount: Int? = null,
    var niceCount: Int? = null,
    var starCount: Int? = null,
    var orderCount: Int? = null,
    @TableField(exist = false)
    override val role: String? = UserTypeEnums.EMPLOYEE.role,
    @TableField(exist = false)
    override val tableName: String? = "user_employee",
) : UserI
