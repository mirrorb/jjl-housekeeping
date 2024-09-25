package cn.lkgc.jjl.websocket

import jakarta.websocket.*
import jakarta.websocket.server.PathParam
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

@ServerEndpoint(value = "/websocket/{role}/{username}")
@Component
class WebSocketService {
    /**
     * 客户端与服务端连接成功
     *
     * @param session
     */
    @OnOpen
    fun onOpen(session: Session, @PathParam("role") role: String, @PathParam("username") username: String) {
        val client = WebSocketClient()
        client.session = session
        client.uri = session.requestURI.toString()
        webSocketMap[role + username] = client
        println("$role: $username 连接成功！")
    }

    /**
     * 客户端与服务端连接关闭
     */
    @OnClose
    fun onClose(session: Session?, @PathParam("role") role: String?, @PathParam("username") username: String?) {
        println("$role: $username 连接关闭！")
    }

    /**
     * 客户端与服务端连接异常
     *
     * @param error
     * @param role
     * @param username
     */
    @OnError
    fun onError(error: Throwable?, @PathParam("role") role: String?, @PathParam("username") username: String?) {
        println("$role: $username 连接失败！")
    }

    /**
     * 客户端向服务端发送消息
     *
     * @param message
     * @throws IOException
     */
    @OnMessage
    @Throws(IOException::class)
    fun onMsg(
        session: Session?, message: String?, @PathParam("role") role: String?, @PathParam("username") username: String?
    ) {
        println("消息来自$role: $username $message")
    }

    companion object {
        private val webSocketMap = ConcurrentHashMap<String, WebSocketClient>()

        /**
         * 向指定客户端发送对象
         *
         * @param role
         * @param username
         * @param obj
         */
        fun sendObject(role: String, username: String, obj: Any?) {
            try {
                val webSocketClient = webSocketMap[role + username]
                if (webSocketClient != null) {
                    val session = webSocketClient.session
                    if (session?.isOpen == true) {
                        session.basicRemote?.sendObject(obj)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                throw RuntimeException(e.message)
            } catch (e: EncodeException) {
                throw RuntimeException(e.message)
            }
        }

        /**
         * 向指定客户端发送消息
         *
         * @param role
         * @param username
         * @param message
         */
        fun sendMessage(role: String, username: String, message: String?) {
            try {
                val webSocketClient = webSocketMap[role + username]
                if (webSocketClient != null) {
                    val session = webSocketClient.session
                    if (session?.isOpen == true) {
                        session.basicRemote?.sendText(message)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                throw RuntimeException(e.message)
            }
        }
    }
}
