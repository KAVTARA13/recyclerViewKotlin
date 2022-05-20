package com.example.homework7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework7.adapter.UserAdapter
import com.example.homework7.api.RestClient
import com.example.homework7.api.dto.ReqResData
import com.example.homework7.api.dto.User
import retrofit2.Call
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleView)

RestClient.getReqResApi.getUsers(2).enqueue(object: retrofit2.Callback<ReqResData<List<User>>>{
    override fun onResponse(
        call: Call<ReqResData<List<User>>>,
        response: Response<ReqResData<List<User>>>
    ) {
    if (response.isSuccessful){
        response.body()?.data?.let {
            recyclerView.adapter=UserAdapter(this@MainActivity,it){
                val intent = Intent(this@MainActivity, UserActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
    }

    override fun onFailure(call: Call<ReqResData<List<User>>>, t: Throwable) {
        TODO("Not yet implemented")
    }
})
    }
}