package cn.lkgc.jjl.enums

enum class OrderStatusEnums(val value: String, val type: StatusType) {
    UNREVIEWED("未审核", StatusType.INTERMEDIATE),
    APPROVED("已审核通过", StatusType.INTERMEDIATE),
    REJECTED("审核未通过", StatusType.INTERMEDIATE),
    IN_PROGRESS("进行中", StatusType.INTERMEDIATE),
    COMPLETED("已完成", StatusType.TERMINAL),
    CANCELLED("已取消", StatusType.TERMINAL);

    enum class StatusType {
        INTERMEDIATE, // 中间状态
        TERMINAL      // 终端状态
    }
}
