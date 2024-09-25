package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 订单实体类
 */
@TableName("biz_order")
data class BizOrder(
    @TableId var id: Int? = null,
    var customer: String? = null,
    var customerMobile: String? = null,
    var employee: String? = null,
    var employeeMobile: String? = null,
    var appointmentAddress: String? = null,
    var appointmentTime: LocalDateTime? = null,
    var appointmentDuration: Double? = null,
    var amount: Double? = null,
    var status: String? = null,
    var createTime: LocalDateTime? = null,
    var reviewTime: LocalDateTime? = null,
    var reviewMsg: String? = null,
    var finalizeTime: LocalDateTime? = null,
    var rate: Double? = null,
) : Serializable
