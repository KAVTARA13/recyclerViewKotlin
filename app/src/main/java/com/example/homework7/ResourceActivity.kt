package com.example.homework7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homework7.api.RestClient
import com.example.homework7.api.dto.ReqResData
import com.example.homework7.api.dto.Resource
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class ResourceActivity: AppCompatActivity() {
    private lateinit var colorName: TextView
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var textView6: TextView
    private lateinit var colorBox: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resource_activity)

        colorName = findViewById(R.id.colorName)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        textView6 = findViewById(R.id.textView6)
        colorBox = findViewById(R.id.colorBox)
        button2 = findViewById(R.id.button2)

        val intent = intent
        val id = intent.getLongExtra("id",0)
        if (id != null) {
            RestClient.getReqResApi.getResourceById(2).enqueue(object :retrofit2.Callback<ReqResData<Resource>>{
                override fun onResponse(
                    call: Call<ReqResData<Resource>>,
                    response: Response<ReqResData<Resource>>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        response.body()!!.data?.toString()?.let {
                            colorName.text = response.body()!!.data?.name
                            textView4.text = response.body()!!.data?.color
                            textView5.text = response.body()!!.data?.pantoneValue
                            textView6.text = response.body()!!.data?.year.toString()
                            button2.setOnClickListener{
                                val intent = Intent(this@ResourceActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ReqResData<Resource>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}