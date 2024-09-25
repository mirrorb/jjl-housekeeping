package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminPageReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminRespVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminUpdateReqVO
import cn.lkgc.jjl.convert.BizAreaConvert
import cn.lkgc.jjl.dal.BizAreaMapper
import cn.lkgc.jjl.entity.BizArea
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BizAreaServiceImpl(
    private val bizAreaMapper: BizAreaMapper, private val bizAreaConvert: BizAreaConvert
) : BizAreaService {

    override fun adminPage(reqVO: BizAreaAdminPageReqVO): PageResult<BizAreaAdminRespVO> =
        PageResultUtils.from(
            bizAreaMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO)
        )

    override fun adminDetail(areaName: String): BizAreaAdminRespVO {
        val doo = bizAreaMapper.selectById(areaName)
        return bizAreaConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: BizAreaAdminUpdateReqVO) {
        // TODO: 当前没有更新需求
//        val doo = bizAreaConvert.convert(reqVO)
//        bizAreaMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: BizAreaAdminCreateReqVO) {
        if (bizAreaMapper.selectById(reqVO.areaName) != null) {
            throw exception("服务区域名已存在")
        }
        val doo = bizAreaConvert.convert(reqVO)
        doo.createTime = LocalDateTime.now()
        bizAreaMapper.insert(doo)
    }

    override fun adminDelete(areaName: String) {
        bizAreaMapper.deleteById(areaName)
    }

    override fun list(): List<String> = bizAreaMapper.selectList(KtQueryWrapper(BizArea())).map { it.areaName ?: "" }

}