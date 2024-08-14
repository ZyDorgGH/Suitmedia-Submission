package com.zycom.suitmediasubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData

import com.zycom.suitmediasubmission.data.response.DataItem

interface UserRepository {

    fun fetchUser(): LiveData<PagingData<DataItem>>

}