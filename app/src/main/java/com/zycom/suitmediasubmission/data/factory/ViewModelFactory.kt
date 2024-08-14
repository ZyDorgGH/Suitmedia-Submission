package com.zycom.suitmediasubmission.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.zycom.suitmediasubmission.data.di.Injection
import com.zycom.suitmediasubmission.ui.screen.second.SecondViewModel
import com.zycom.suitmediasubmission.ui.screen.third.ThirdViewModel

class ViewModelFactory(
private val injection: Injection
) : ViewModelProvider.NewInstanceFactory() {

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(ThirdViewModel::class.java) -> ThirdViewModel(
//                injection.userRepository
//            )
//            modelClass.isAssignableFrom(SecondViewModel::class.java) -> SecondViewModel()
//
//            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//        } as T
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(ThirdViewModel::class.java) -> ThirdViewModel(
                injection.userRepository
            )
            modelClass.isAssignableFrom(SecondViewModel::class.java) -> SecondViewModel(
                extras.createSavedStateHandle()
            )

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }
}