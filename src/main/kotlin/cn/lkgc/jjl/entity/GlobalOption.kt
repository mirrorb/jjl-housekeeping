package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * 系统选项实体类
 */
@TableName("global_option")
data class GlobalOption(
    @TableId val item: String? = null,
    val option: String? = null,
    val sort: Int? = null,
) : Serializable
