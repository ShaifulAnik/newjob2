package com.example.newjob2.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfile)

    @Delete
    suspend fun deleteProfile(profile: UserProfile)

    @Query("SELECT * FROM user_profiles ORDER BY id DESC")
    fun getAllProfiles(): LiveData<List<UserProfile>>

    @Query("SELECT * FROM user_profiles WHERE id = :id LIMIT 1")
    fun getProfileById(id: Int): LiveData<UserProfile>

    @Query("SELECT COUNT(*) FROM user_profiles")
    fun getProfileCount(): LiveData<Int>
}