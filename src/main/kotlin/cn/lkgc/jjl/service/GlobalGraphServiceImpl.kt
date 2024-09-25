package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.globalgraph.GlobalGraphEmployeeRespVO
import cn.lkgc.jjl.convert.GlobalGraphConvert
import org.springframework.stereotype.Service

@Service
class GlobalGraphServiceImpl(
    private val userEmployeeService: UserEmployeeService,
    private val globalGraphConvert: GlobalGraphConvert
) : GlobalGraphService {

    override fun employeeAll(): List<GlobalGraphEmployeeRespVO> =
        globalGraphConvert.convertList(userEmployeeService.limit3DO())

}