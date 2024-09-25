package cn.lkgc.jjl.controller.vo.bizcomment

import lombok.Data

@Data
class BizCommentCreateReqVO (
    val target: String? = null,
    val targetType: String? = null,
    val parentId: Int? = null,
    val text: String? = null
)

