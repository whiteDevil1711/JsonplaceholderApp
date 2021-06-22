package com.kuldeep.makwana.api

import com.kuldeep.makwana.home.model.ImageModel
import retrofit2.http.GET
import retrofit2.Call


/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 22-06-2021.
 * PS: Not easy to write code, please indicate.
 */
interface ApiService {

    @GET("photos")
    fun getImageList(): Call<List<ImageModel>>
}