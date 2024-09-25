package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 服务商家实体类
 */
@TableName("biz_merchant")
data class BizMerchant(
    @TableId var merchantName: String? = null,
    var address: String? = null,
    var telephone: String? = null,
    var picture: String? = null,
    var description: String? = null,
    var createTime: LocalDateTime? = null,
) : Serializable
