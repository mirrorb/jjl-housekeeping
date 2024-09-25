package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizmerchant.*
import cn.lkgc.jjl.convert.BizMerchantConvert
import cn.lkgc.jjl.dal.BizMerchantMapper
import cn.lkgc.jjl.entity.BizMerchant
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.JsoupUtils
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BizMerchantServiceImpl(
    private val bizMerchantMapper: BizMerchantMapper, private val bizMerchantConvert: BizMerchantConvert
) : BizMerchantService {

    override fun list(): List<String> =
        bizMerchantMapper.selectList(KtQueryWrapper(BizMerchant())).map { it.merchantName ?: "" }

    override fun adminPage(reqVO: BizMerchantAdminPageReqVO): PageResult<BizMerchantAdminRespVO> = PageResultUtils.from(
        bizMerchantMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO)
    )

    override fun adminDetail(merchantName: String): BizMerchantAdminRespVO {
        val doo = bizMerchantMapper.selectById(merchantName)
        return bizMerchantConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: BizMerchantAdminUpdateReqVO) {
        val doo = bizMerchantConvert.convert(reqVO)
        bizMerchantMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: BizMerchantAdminCreateReqVO) {
        if (bizMerchantMapper.selectById(reqVO.merchantName) != null) {
            throw exception("家政商家已存在")
        }
        val doo = bizMerchantConvert.convert(reqVO)
        doo.createTime = LocalDateTime.now()
        bizMerchantMapper.insert(doo)
    }

    override fun adminDelete(merchantName: String) {
        bizMerchantMapper.deleteById(merchantName)
    }

    override fun frontLimit(): List<BizMerchantFrontRespVO> {
        val dos = bizMerchantMapper.selectList(
            KtQueryWrapper(BizMerchant()).orderByDesc(
                BizMerchant::createTime
            ).last("LIMIT 3")
        )
        dos.forEach { it.description = JsoupUtils.getTextFromHtml(it.description ?: "") }
        return bizMerchantConvert.convertList2(dos)
    }

    override fun frontPage(reqVO: BizMerchantFrontPageReqVO): PageResult<BizMerchantFrontRespVO> {
        val doPage = PageResultUtils.from(
            bizMerchantMapper.selectFrontPage(
                Page(reqVO.current, reqVO.pageSize)
            )
        )
        doPage.list.forEach { it.description = JsoupUtils.getTextFromHtml(it.description ?: "") }
        return doPage
    }

    override fun frontDetail(merchantName: String): BizMerchantFrontRespVO {
        val doo = bizMerchantMapper.selectById(merchantName) ?: throw exception("家政商家不存在")
        return bizMerchantConvert.convert2(doo)
    }

}