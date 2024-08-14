package com.zycom.suitmediasubmission.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zycom.suitmediasubmission.data.response.DataItem
import com.zycom.suitmediasubmission.databinding.ItemRowUserBinding
import com.zycom.suitmediasubmission.ui.adapter.UserListAdapter.MyViewHolder.Companion.DIFF_CALLBACK

class UserListAdapter ( private val onClick: (DataItem, optionsCompat: ActivityOptionsCompat) -> Unit
) : PagingDataAdapter<DataItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = getItem(position)
        if (list != null) {
            holder.bind(list)
        }
    }

    class MyViewHolder(
        private val binding: ItemRowUserBinding,
        private val onClick: (DataItem, optionsCompat: ActivityOptionsCompat) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: DataItem) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(user.avatar)
                    .into(imgItemPhoto)

                tvUsername.text = "${user.firstName} ${user.lastName}"
                tvEmail.text = user.email

                root.setOnClickListener {
                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(imgItemPhoto, "photoUrl"),
                            Pair(tvUsername, "username"),
                            Pair(tvEmail, "email")

                        )
                    onClick(user, optionsCompat)
                }
            }
        }
        companion object {
            val DIFF_CALLBACK= object : DiffUtil.ItemCallback<DataItem>() {
                override fun areItemsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {
                    return oldItem.firstName == newItem.firstName
                }

                override fun areContentsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }


}