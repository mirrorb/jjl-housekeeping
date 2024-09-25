package cn.lkgc.jjl.service

import cn.lkgc.jjl.enums.GlobalOptionsEnums

interface GlobalOptionsService {
    fun listOption(item: GlobalOptionsEnums): List<String>
}