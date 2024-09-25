package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizstore.BizStoreUpdateReqVO

interface BizStoreService {
    fun update(reqVO: BizStoreUpdateReqVO)
}