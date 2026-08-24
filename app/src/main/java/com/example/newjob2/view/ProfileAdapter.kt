package com.example.newjob2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newjob2.data.UserProfile
import com.example.newjob2.databinding.ItemProfileBinding

class ProfileAdapter(
    private val onItemClick: (UserProfile) -> Unit,
    private val onEditClick: (UserProfile) -> Unit,
    private val onDeleteClick: (UserProfile) -> Unit
) : ListAdapter<UserProfile, ProfileAdapter.ProfileViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }

    inner class ProfileViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profile: UserProfile) {
            binding.tvName.text = profile.name
            binding.tvEmail.text = profile.email

            binding.root.setOnClickListener { onItemClick(profile) }
            binding.btnEdit.setOnClickListener { onEditClick(profile) }
            binding.btnDelete.setOnClickListener { onDeleteClick(profile) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean = oldItem == newItem
    }
}