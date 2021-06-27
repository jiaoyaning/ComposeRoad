package com.jyn.composeroad

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.FlowableConverter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * 状态管理
 * https://developer.android.google.cn/jetpack/compose/state
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {


    //region 状态管理之 liveData
    /**
     * Compose存储状态 之 LiveData
     * https://stackoverflow.com/questions/59096435/using-livedata-as-state-inside-jetpack-compose-functions
     */
    var liveData: MutableLiveData<Int> = MutableLiveData(0)

    fun liveDataAdd() {
        liveData.value = liveData.value?.plus(1)
    }
    //endregion

    //region 状态管理之Flow
    //endregion

    //region 状态管理之 RxJava2
    private lateinit var emitter: ObservableEmitter<Int>
    var observable = Observable.create<Int> { emitter = it }

    fun rxJava2Add(value: Int) {
        emitter.onNext(value + 1)
    }
    //endregion
}