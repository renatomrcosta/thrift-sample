package com.xunfos.ktorThrift.controller.async

import com.xunfos.ktorThrift.controller.ThriftContract
import com.xunfos.playground.thrift.GetUserRequest
import com.xunfos.playground.thrift.GetUserResponse
import com.xunfos.playground.thrift.GetUsersResponse
import com.xunfos.playground.thrift.PingResponse
import com.xunfos.playground.thrift.PlaygroundService
import com.xunfos.playground.thrift.User
import com.xunfos.sbpplayground.util.trace
import org.apache.thrift.async.AsyncMethodCallback
import kotlin.random.Random

class ThriftHandler : PlaygroundService.AsyncIface, ThriftContract {
    private val rng = Random(System.currentTimeMillis())

    override fun ping(resultHandler: AsyncMethodCallback<PingResponse>) {
        trace("Pong!")
        resultHandler.onComplete(PingResponse(true))
    }

    override fun getUser(request: GetUserRequest, resultHandler: AsyncMethodCallback<GetUserResponse>) {
        val workId = rng.nextLong()

        trace("[workId: $workId] Starting getUser Fun. ${WORK_TIME}ms of work")
        Thread.sleep(WORK_TIME)
        trace("[workId: $workId] Finishing getUser Fun.")

        // Suppose a query would be made
        resultHandler.onComplete(GetUserResponse().apply {
            this.user = User().apply {
                this.id = request.id
                this.name = "banana"
            }
        })
    }

    override fun getUsers(resultHandler: AsyncMethodCallback<GetUsersResponse>) {
        val workId = rng.nextLong()

        trace("[workId: $workId] Starting getUsers Fun. ${WORK_TIME}ms of work")
        Thread.sleep(WORK_TIME)
        trace("[workId: $workId] Finishing getUsers Fun.")

        resultHandler.onComplete(GetUsersResponse().apply {
            this.users = listOf(
                User().apply {
                    this.id = "1"
                    this.name = "banana"
                })
        })
    }

    companion object {
        private const val WORK_TIME = 5_000L
    }
}
