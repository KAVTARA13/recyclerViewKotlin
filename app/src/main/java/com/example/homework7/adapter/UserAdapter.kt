package com.example.homework7.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.homework7.R
import com.example.homework7.api.dto.User
import com.squareup.picasso.Picasso

class UserAdapter(private val context: Context, private val users: List<User>, val listener: (User) -> Unit): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val textView = itemView.findViewById<TextView>(R.id.textView)
        private val textView2 = itemView.findViewById<TextView>(R.id.textView2)
        private val img = itemView.findViewById<ImageView>(R.id.imageView)
        private val userLayout = itemView.findViewById<ConstraintLayout>(R.id.user)

        fun onBind(user: User, listener: (User) -> Unit){
            textView.text = user.firstName+" "+user.lastName
            textView2.text = user.email
            Picasso.get().load(user.avatar).into(img);
            userLayout.setOnClickListener{listener(user)}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(users[position],listener)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}