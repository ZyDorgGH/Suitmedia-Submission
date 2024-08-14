package com.zycom.suitmediasubmission.ui.screen.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zycom.suitmediasubmission.data.repository.UserRepository
import com.zycom.suitmediasubmission.data.response.DataItem


class ThirdViewModel (
    private val repository: UserRepository,
    ): ViewModel() {

    fun story(): LiveData<PagingData<DataItem>> =
        repository.fetchUser().cachedIn(viewModelScope)

}
