package com.example.githubuserapp

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.databinding.ItemRowUserBinding
import java.io.File

class ListUserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    var users = arrayListOf<User>()

//    private lateinit var binding: ActivityMainBinding

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
//        var imgAvatar: ImageView = itemView.findViewById(R.id.img_item_photo)
//        var tvUsername: TextView = itemView.findViewById(R.id.tv_user_username)
//        var tvName: TextView = itemView.findViewById(R.id.tv_user_name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (username, name, avatar) = listUser[position]
        holder.binding.tvUserName.text = name
        holder.binding.tvUserUsername.text = username
//        holder.binding.imgItemPhoto.setImageResource(avatar)
        Glide.with(holder.binding.root)
            .load(avatar)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)
//        holder.tvUsername.text = username
//        holder.tvName.text = name

//        Log.d("img")

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[position]) }



    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}

