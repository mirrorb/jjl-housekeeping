package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentCreateReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentRespVO
import cn.lkgc.jjl.entity.BizComment
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BizCommentConvert {
    @Mapping(target = "replies", expression = "java(java.util.Collections.emptyList())")
    fun convert(bizComment: BizComment): BizCommentRespVO
    fun convertList(bizComments: List<BizComment>): List<BizCommentRespVO>
    fun convert(reqVO: BizCommentCreateReqVO): BizComment
}