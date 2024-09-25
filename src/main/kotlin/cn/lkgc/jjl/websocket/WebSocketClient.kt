package cn.lkgc.jjl.websocket

import jakarta.websocket.Session


data class WebSocketClient (
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    var session: Session? = null,
    //连接的uri
    var uri: String? = null
)