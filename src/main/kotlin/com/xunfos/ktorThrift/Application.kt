package com.xunfos.ktorThrift

import com.linecorp.armeria.common.HttpResponse
import com.linecorp.armeria.common.SessionProtocol
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.thrift.THttpService
import com.xunfos.ktorThrift.controller.ThriftContract
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Duration
import com.xunfos.ktorThrift.controller.async.ThriftHandler as AsyncHandler
import com.xunfos.ktorThrift.controller.coroutines.ThriftHandler as CoHandler
import com.xunfos.ktorThrift.controller.sync.ThriftHandler as SyncHandler

@KtorExperimentalAPI
fun main(args: Array<String>) {
    configureArmeria()
}

fun configureArmeria() {
    val syncServer = configureServer("/api", 7070, SyncHandler())
    val asyncServer = configureServer("/api", 8080, AsyncHandler())
    val coServer = configureServer("/api", 9090, CoHandler())

    runBlocking {
        launch {
            syncServer.build().start()
        }
        launch {
            asyncServer.build().start()
        }
        launch {
            coServer.build().start()
        }
    }
}

fun configureServer(route: String, port: Int, handler: ThriftContract) = Server.builder()
    .service(route, THttpService.of(handler))
    .port(port, SessionProtocol.HTTP)
    .requestTimeout(Duration.ofSeconds(2))
    .serviceUnder("/health") { _, _ ->
        HttpResponse.of("OK")
    }

//
// suspend fun configureSocket() = coroutineScope {
//     val server = aSocket(ActorSelectorManager(Dispatchers.Default)).tcp().bind(InetSocketAddress(8081))
//     while(isActive) {
//         val socket = server.accept()
//         launch {
//             val input = socket.openReadChannel()
//             val output = socket.openWriteChannel(true)
//         }
//     }
// }
// fun configForNow() {
//     val asyncProcessor = PlaygroundService.AsyncProcessor<PlaygroundService.AsyncIface>(AsyncHandler())
//     val coProcessor = PlaygroundService.AsyncProcessor<PlaygroundService.AsyncIface>(CoHandler())
// }
//
// fun configureThrift() {
//     val processor = PlaygroundService.Processor<PlaygroundService.Iface>(SyncHandler())
//     val protocolFactory = TBinaryProtocol.Factory()
//     val servlet = TServlet(processor, protocolFactory)
// }
