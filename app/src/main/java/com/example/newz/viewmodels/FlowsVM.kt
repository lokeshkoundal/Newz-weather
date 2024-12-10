package com.example.newz.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowsVM : ViewModel() {

    val numberFlow: Flow<Int> = flow{
        repeat(20){
            emit(it+1)
            delay(1000)
        }
    }

}