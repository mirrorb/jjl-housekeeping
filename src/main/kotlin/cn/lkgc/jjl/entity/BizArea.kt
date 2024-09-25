package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 服务区域实体类
 */
@TableName("biz_area")
data class BizArea(
    @TableId var areaName: String? = null,
    var createTime: LocalDateTime? = null,
) : Serializable