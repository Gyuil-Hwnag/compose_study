package com.example.compose_study.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

// Rxjava 의 takeUntil 함수와 같은 동작을 함.
fun <T> Flow<T>.takeUntil(predicate: (T) -> Boolean): Flow<T> = flow {
    this@takeUntil.collect { value ->
        emit(value)
        if (predicate(value)) {
            currentCoroutineContext().cancel()
        }
    }
}

class FlowExt {
    private suspend fun collect1() {
        flowTest1.takeUntil { it == 10 }
            .collect {
                delay(10)
                println("takeUntil : $it")
            }
    }

    private suspend fun collect2() {
        flowTest2.collect {
            delay(10)
            println("collect : $it")
        }
    }

    fun invoke() {
        CoroutineScope(Dispatchers.Main).launch {
            // 10 까지 방출 후 종료
            launch { collect1() }
            // collect1이 종료된 후에도 방출 하여 100 까지 모두 방출
            launch { collect2() }
        }
    }

    companion object {
        private val flowTest1 = (0 until 100).asFlow()
        private val flowTest2 = (0 until 100).asFlow()
    }
}