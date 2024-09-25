package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.globalgraph.GlobalGraphEmployeeRespVO

interface GlobalGraphService {
    fun employeeAll(): List<GlobalGraphEmployeeRespVO>
}