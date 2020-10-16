package com.example.kotlin_recyclerview_retrofit.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.example.kotlin_recyclerview_retrofit.adapter.FragmentOneAdapter
import com.example.kotlin_recyclerview_retrofit.databinding.FragmentOneBinding
import com.example.kotlin_recyclerview_retrofit.models.Articles
import com.example.kotlin_recyclerview_retrofit.models.News
import com.example.kotlin_recyclerview_retrofit.others.ColorPicker
import com.example.kotlin_recyclerview_retrofit.retrofit.RetrofitClientInstance
import com.example.kotlin_recyclerview_retrofit.retrofit.Services
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.fragment_one.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class FragmentOne : Fragment() {

    lateinit var binding: FragmentOneBinding
    lateinit var adapter : FragmentOneAdapter
    lateinit var arrayList : ArrayList<Articles>
    var pageNo = 1
    var totalResult = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayList = ArrayList()

        adapter = FragmentOneAdapter(requireContext(), arrayList)
        rv_one.adapter = adapter

        //stackLayoutManager
        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        rv_one.layoutManager = layoutManager
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                Log.d("shantanu mishra" , "first visible position ${layoutManager.getFirstVisibleItemPosition().toString()}")
                Log.d("shantanu mishra" , "first visible position ${layoutManager.itemCount}")
                if(totalResult> layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount-5 )
                {
                    pageNo++
                    getNews()
                }
            }
        })
        getNews()
    }

    private fun getNews() {
        Log.d("shantanu mishra" , "call page 2  ${pageNo}")
        val services = RetrofitClientInstance.getRetrofitInstance().create(Services::class.java)
        val call = services.getNews("in", pageNo)

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val data = response.body()
                totalResult = data!!.totalResults
                arrayList.addAll(data.articles)
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("shantanu", "failed to load data")
            }
        })
    }


}