package com.jyn.composeroad

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

class MainViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Compose存储状态 之 LiveData
     * https://stackoverflow.com/questions/59096435/using-livedata-as-state-inside-jetpack-compose-functions
     */
    var liveData: MutableLiveData<Int> = MutableLiveData(0)

    fun liveDataAdd() {
        liveData.value = liveData.value?.plus(1)
    }

    lateinit var emitter: ObservableEmitter<Int>
    var observable = Observable.create<Int> { emitter = it }

    fun rxJava2Add(value: Int) {
        emitter.onNext(value + 1)
    }
}