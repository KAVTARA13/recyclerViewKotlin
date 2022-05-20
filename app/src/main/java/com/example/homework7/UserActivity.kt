package com.example.homework7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homework7.api.RestClient
import com.example.homework7.api.dto.ReqResData
import com.example.homework7.api.dto.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response


class UserActivity: AppCompatActivity()  {
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var email:TextView
    private lateinit var image: ImageView
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        email = findViewById(R.id.email)
        image = findViewById(R.id.image)
        back = findViewById(R.id.back)
        val intent = intent
        val id = intent.getLongExtra("id",0)
        if (id != null) {
            RestClient.getReqResApi.getUserById(id.toInt()).enqueue(object :retrofit2.Callback<ReqResData<User>>{

                override fun onResponse(
                    call: Call<ReqResData<User>>,
                    response: Response<ReqResData<User>>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        response.body()!!.data?.toString()?.let {
                            firstName.text = response.body()!!.data?.firstName
                            lastName.text = response.body()!!.data?.lastName
                            email.text = response.body()!!.data?.email
                            Picasso.get().load(response.body()!!.data?.avatar).into(image);
                            back.setOnClickListener{
                                val intent = Intent(this@UserActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ReqResData<User>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }
}