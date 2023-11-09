package com.example.infobyteassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WatchListFragment : Fragment() {

    private lateinit var watchlistview: View
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        watchlistview =  inflater.inflate(R.layout.fragment_watch_list, container, false)

        recyclerView = watchlistview.findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://script.googleusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if API call is success, then use API data and show in your app
                var responceBody = response.body()
                // if (productList != null) ke badle = responceBody?.products!

              val productList = responceBody?.subList(0,100)!!

                myAdapter = MyAdapter(this@WatchListFragment, productList)
                recyclerView.adapter = myAdapter

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if API call is fails
            //    Toast.makeText(this@WatchListFragment, "API FAILED", Toast.LENGTH_SHORT).show()

            }
        })



        return watchlistview
    }
}