package com.zycom.suitmediasubmission.ui.screen.second

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SecondViewModel(private val savedStateHandle: SavedStateHandle) :ViewModel() {

    fun setName(name: String){
        savedStateHandle[NAME_KEY] = name
    }

    fun getName(): String? = savedStateHandle[NAME_KEY]

}

private const val NAME_KEY = "name_key"