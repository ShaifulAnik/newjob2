package com.example.newjob2.data

import androidx.lifecycle.LiveData

class ProfileRepository(private val profileDao: ProfileDao) {

    val allProfiles: LiveData<List<UserProfile>> = profileDao.getAllProfiles()
    val totalProfilesCount: LiveData<Int> = profileDao.getProfileCount()

    suspend fun insertOrUpdate(profile: UserProfile) {
        profileDao.insertOrUpdateProfile(profile)
    }

    suspend fun delete(profile: UserProfile) {
        profileDao.deleteProfile(profile)
    }

    fun getProfileById(id: Int): LiveData<UserProfile> {
        return profileDao.getProfileById(id)
    }
}