package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.UIKit.UIDevice
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

actual fun platformName(): String {
    return UIDevice.currentDevice.systemName() +
            " " +
            UIDevice.currentDevice.systemVersion
}

actual class AppDispatchersImpl : AppDispatchers {
    @SharedImmutable
    override val main: CoroutineDispatcher = NSQueueDispatcher(dispatch_get_main_queue())

    @SharedImmutable
    override val io: CoroutineDispatcher = NSQueueDispatcher(dispatch_get_main_queue())

    class NSQueueDispatcher(
        @SharedImmutable private val dispatchQueue: dispatch_queue_t
    ) : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            dispatch_async(dispatchQueue) {
                block.run()
            }
        }
    }
}
