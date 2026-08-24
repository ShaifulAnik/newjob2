package com.example.newjob2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newjob2.data.AppDatabase
import com.example.newjob2.data.ProfileDao
import com.example.newjob2.data.ProfileRepository
import com.example.newjob2.data.UserProfile
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProfileRepository
    val allProfiles: LiveData<List<UserProfile>>
    val totalProfilesCount: LiveData<Int>

    init {
        val profileDao: ProfileDao = AppDatabase.getDatabase(application).profileDao()
        repository = ProfileRepository(profileDao)
        allProfiles = repository.allProfiles
        totalProfilesCount = repository.totalProfilesCount
    }

    fun insertOrUpdate(profile: UserProfile) = viewModelScope.launch {
        repository.insertOrUpdate(profile)
    }

    fun delete(profile: UserProfile) = viewModelScope.launch {
        repository.delete(profile)
    }

    fun getProfileById(id: Int): LiveData<UserProfile> {
        return repository.getProfileById(id)
    }
}