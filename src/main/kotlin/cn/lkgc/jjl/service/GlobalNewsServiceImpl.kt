package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.globalnews.*
import cn.lkgc.jjl.convert.GlobalNewsConvert
import cn.lkgc.jjl.dal.GlobalNewsMapper
import cn.lkgc.jjl.entity.GlobalNews
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.JsoupUtils
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GlobalNewsServiceImpl(
    private val globalNewsMapper: GlobalNewsMapper, private val globalNewsConvert: GlobalNewsConvert
) : GlobalNewsService {

    override fun listGlobalNews(): List<String> =
        globalNewsMapper.selectList(KtQueryWrapper(GlobalNews())).map { it.newsName ?: "" }

    override fun adminPage(reqVO: GlobalNewsAdminPageReqVO): PageResult<GlobalNewsAdminRespVO> {
        return PageResultUtils.from(
            globalNewsMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO)
        )
    }

    override fun adminDetail(newsName: String): GlobalNewsAdminRespVO {
        val doo = globalNewsMapper.selectById(newsName) ?: throw exception("公告不存在")
        return globalNewsConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: GlobalNewsAdminUpdateReqVO) {
        val doo = globalNewsConvert.convert(reqVO)
        globalNewsMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: GlobalNewsAdminCreateReqVO) {
        if (globalNewsMapper.selectById(reqVO.newsName) != null) {
            throw exception("公告已存在")
        }
        val doo = globalNewsConvert.convert(reqVO)
        doo.createTime = LocalDateTime.now()
        globalNewsMapper.insert(doo)
    }

    override fun adminDelete(newsName: String) {
        globalNewsMapper.deleteById(newsName)
    }

    override fun frontLimit(): List<GlobalNewsFrontRespVO> {
        val dos = globalNewsMapper.selectList(
            KtQueryWrapper(GlobalNews()).orderByDesc(
                GlobalNews::createTime
            ).last("LIMIT 3")
        )
        dos.forEach { it.description = JsoupUtils.getTextFromHtml(it.description ?: "") }
        return globalNewsConvert.convertList2(dos)
    }

    override fun frontPage(reqVO: GlobalNewsFrontPageReqVO): PageResult<GlobalNewsFrontRespVO> {
        val doPage = PageResultUtils.from(
            globalNewsMapper.selectFrontPage(Page(reqVO.current, reqVO.pageSize))
        )
        doPage.list.forEach { it.description = JsoupUtils.getTextFromHtml(it.description ?: "") }
        return doPage
    }

    override fun frontDetail(newsName: String): GlobalNewsFrontRespVO {
        val doo = globalNewsMapper.selectById(newsName) ?: throw exception("公告不存在")
        return globalNewsConvert.convert2(doo)
    }

}