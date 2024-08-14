package com.zycom.suitmediasubmission.data.di

import android.content.Context
import com.zycom.suitmediasubmission.data.local.room.SuitmediaDatabase
import com.zycom.suitmediasubmission.data.repository.DefaultUserRepository
import com.zycom.suitmediasubmission.data.repository.UserRepository
import com.zycom.suitmediasubmission.data.retrofit.ApiConfig

class Injection (
    context: Context
    ) {
        private val api = ApiConfig.getApiService()
        private val database = SuitmediaDatabase.getDatabase(context)


        val userRepository: UserRepository = DefaultUserRepository(
            api = api,
            database = database
        )
    }