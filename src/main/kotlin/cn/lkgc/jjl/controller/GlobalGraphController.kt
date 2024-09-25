package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.globalgraph.GlobalGraphEmployeeRespVO
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.service.GlobalGraphService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 系统图表控制器
 */
@RequestMapping("/global/graph")
@RestController
class GlobalGraphController(
    private val globalGraphService: GlobalGraphService,
) {

    @GetMapping("/employee-all")
    fun employeeAll() : CommonResult<List<GlobalGraphEmployeeRespVO>> {
        return success(globalGraphService.employeeAll())
    }

}