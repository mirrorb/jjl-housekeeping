package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.globalcarousel.GlobalCarouselAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.globalcarousel.GlobalCarouselAdminRespVO
import cn.lkgc.jjl.controller.vo.globalcarousel.GlobalCarouselAdminUpdateReqVO
import cn.lkgc.jjl.controller.vo.globalcarousel.GlobalCarouselFrontRespVO
import cn.lkgc.jjl.entity.GlobalCarousel
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GlobalCarouselConvert {
    fun convert(doo: GlobalCarousel): GlobalCarouselAdminRespVO
    fun convert(reqVO: GlobalCarouselAdminUpdateReqVO): GlobalCarousel
    fun convert(reqVO: GlobalCarouselAdminCreateReqVO): GlobalCarousel
    fun convertList(dos: List<GlobalCarousel>): List<GlobalCarouselAdminRespVO>
    fun convert2(doo2: GlobalCarousel): GlobalCarouselFrontRespVO
    fun convertList2(selectList: List<GlobalCarousel>): List<GlobalCarouselFrontRespVO>
}