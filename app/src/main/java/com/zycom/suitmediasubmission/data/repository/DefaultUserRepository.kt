package com.zycom.suitmediasubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.zycom.suitmediasubmission.data.UserMediator
import com.zycom.suitmediasubmission.data.local.room.SuitmediaDatabase
import com.zycom.suitmediasubmission.data.response.DataItem
import com.zycom.suitmediasubmission.data.retrofit.ApiService

class DefaultUserRepository(
    private val api: ApiService,
    private val database: SuitmediaDatabase
) : UserRepository {

    override fun fetchUser(): LiveData<PagingData<DataItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = UserMediator(database, api),
            pagingSourceFactory = {
                database.userDao().getAllUser()
            }
        ).liveData
    }

}