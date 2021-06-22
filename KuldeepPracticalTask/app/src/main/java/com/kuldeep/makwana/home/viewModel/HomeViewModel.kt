package com.kuldeep.makwana.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuldeep.makwana.api.ApiService
import com.kuldeep.makwana.api.RetrofitBuilder
import com.kuldeep.makwana.home.model.ImageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 22-06-2021.
 * PS: Not easy to write code, please indicate.
 */
class HomeViewModel : ViewModel() {

    private var apiInterface:ApiService?=null
    var postModelListLiveData : LiveData<List<ImageModel>>?=null

    init {
        apiInterface = RetrofitBuilder.getApiClient().create(ApiService::class.java)
        postModelListLiveData = MutableLiveData()
    }

    fun imagesData(): LiveData<List<ImageModel>> {
        val data = MutableLiveData<List<ImageModel>>()

        apiInterface?.getImageList()?.enqueue(object : Callback<List<ImageModel>> {

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<ImageModel>>,
                response: Response<List<ImageModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
        })
        return data
    }

    fun fetchImages(){
        postModelListLiveData = imagesData()
    }

}