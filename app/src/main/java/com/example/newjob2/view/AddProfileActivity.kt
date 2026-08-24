package com.example.newjob2.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newjob2.data.UserProfile
import com.example.newjob2.databinding.ActivityAddProfileBinding
import com.example.newjob2.viewmodel.ProfileViewModel

class AddProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var profileId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if editing an existing profile
        if (intent.hasExtra("PROFILE_ID")) {
            profileId = intent.getIntExtra("PROFILE_ID", 0)
            binding.etName.setText(intent.getStringExtra("PROFILE_NAME"))
            binding.etEmail.setText(intent.getStringExtra("PROFILE_EMAIL"))
            binding.etPhone.setText(intent.getStringExtra("PROFILE_PHONE"))
            binding.btnSave.text = "Update Profile"
        }

        binding.btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val profile = UserProfile(id = profileId, name = name, email = email, phone = phone)
        viewModel.insertOrUpdate(profile)

        Toast.makeText(this, "Profile Saved Successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}