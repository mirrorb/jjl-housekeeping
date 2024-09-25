package cn.lkgc.jjl.controller.vo.bizcomment

import cn.lkgc.jjl.framework.pojo.PageParam

class BizCommentPageReqVO(
    val target: String? = null,
    val targetType: String? = null
) : PageParam()