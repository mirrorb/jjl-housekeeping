package cn.lkgc.jjl.controller.vo.bizcomment

import java.io.Serializable

data class BizCommentRespVO(
    var id: Int,
    var username: String,
    var role: String,
    var avatar: String?,
    var text: String,
    var replies: List<cn.lkgc.jjl.controller.vo.bizcomment.BizCommentRespVO>
) : Serializable
