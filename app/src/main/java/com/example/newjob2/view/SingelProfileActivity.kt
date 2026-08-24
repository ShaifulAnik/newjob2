package com.example.newjob2.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newjob2.databinding.ActivitySingleProfileBinding
import com.example.newjob2.viewmodel.ProfileViewModel

class SingleProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileId = intent.getIntExtra("PROFILE_ID", -1)

        if (profileId != -1) {
            viewModel.getProfileById(profileId).observe(this) { profile ->
                profile?.let {
                    binding.tvDetailName.text = "Name: ${it.name}"
                    binding.tvDetailEmail.text = "Email: ${it.email}"
                    binding.tvDetailPhone.text = "Phone: ${it.phone}"
                }
            }
        }
    }
}