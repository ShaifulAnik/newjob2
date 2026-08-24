package com.example.newjob2.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newjob2.data.UserProfile
import com.example.newjob2.databinding.ActivityProfileListBinding
import com.example.newjob2.viewmodel.ProfileViewModel

class ProfileListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileListBinding
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Observe total profiles count
        viewModel.totalProfilesCount.observe(this) { count ->
            binding.tvTotalCount.text = "Total: ${count ?: 0}"
        }

        // Observe profile list data
        viewModel.allProfiles.observe(this) { profiles ->
            adapter.submitList(profiles)
        }

        binding.fabAddProfile.setOnClickListener {
            val intent = Intent(this, AddProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ProfileAdapter(
            onItemClick = { profile ->
                val intent = Intent(this, SingleProfileActivity::class.java).apply {
                    putExtra("PROFILE_ID", profile.id)
                }
                startActivity(intent)
            },
            onEditClick = { profile ->
                val intent = Intent(this, AddProfileActivity::class.java).apply {
                    putExtra("PROFILE_ID", profile.id)
                    putExtra("PROFILE_NAME", profile.name)
                    putExtra("PROFILE_EMAIL", profile.email)
                    putExtra("PROFILE_PHONE", profile.phone)
                }
                startActivity(intent)
            },
            onDeleteClick = { profile ->
                viewModel.delete(profile)
            }
        )
        binding.rvProfiles.layoutManager = LinearLayoutManager(this)
        binding.rvProfiles.adapter = adapter
    }
}