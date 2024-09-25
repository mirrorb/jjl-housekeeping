package cn.lkgc.jjl.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * 点赞收藏实体类
 */
@TableName("biz_store")
data class BizStore(
    @TableId val key: String? = null,
    val customer: String? = null,
    val employee: String? = null,
    val type: String? = null,
) : Serializable
