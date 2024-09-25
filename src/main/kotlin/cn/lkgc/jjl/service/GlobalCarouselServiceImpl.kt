package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.globalcarousel.*
import cn.lkgc.jjl.convert.GlobalCarouselConvert
import cn.lkgc.jjl.dal.GlobalCarouselMapper
import cn.lkgc.jjl.entity.GlobalCarousel
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GlobalCarouselServiceImpl(
    private val globalCarouselMapper: GlobalCarouselMapper, private val globalCarouselConvert: GlobalCarouselConvert
) : GlobalCarouselService {

    override fun adminPage(reqVO: GlobalCarouselAdminPageReqVO): PageResult<GlobalCarouselAdminRespVO> =
        PageResultUtils.from(
            globalCarouselMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO)
        )

    override fun adminDetail(name: String): GlobalCarouselAdminRespVO {
        val doo = globalCarouselMapper.selectById(name)
        return globalCarouselConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: GlobalCarouselAdminUpdateReqVO) {
        val doo = globalCarouselConvert.convert(reqVO)
        globalCarouselMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: GlobalCarouselAdminCreateReqVO) {
        if (globalCarouselMapper.selectById(reqVO.name) != null) {
            throw exception("轮播图名已存在")
        }
        val doo = globalCarouselConvert.convert(reqVO)
        doo.createTime = LocalDateTime.now()
        globalCarouselMapper.insert(doo)
    }

    override fun adminDelete(name: String) {
        globalCarouselMapper.deleteById(name)
    }

    override fun frontList(): List<GlobalCarouselFrontRespVO> = globalCarouselConvert.convertList2(
        globalCarouselMapper.selectList(KtQueryWrapper(GlobalCarousel())).filterNotNull()
    )

}