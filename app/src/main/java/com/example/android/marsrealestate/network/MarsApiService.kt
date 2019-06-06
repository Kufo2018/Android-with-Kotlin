/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"

/**
 * Uses Retrofit.Builder to create a Retrofit object
 * using a Moshi converter with our Moshi
 * object pointing to the desired URL
 **/
private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

/**
 * Creates a MarsApiService interface,
 * and defines a getProperties() method to request the JSON response string.

 *  Annotates the method with @GET,
 *  specifying the endpoint for the JSON real estate response,
 *  and creates the Retrofit Call object that will start the HTTP request.
 **/
interface MarsApiService {

    /**
     * Returns a Retrofit callback that delivers a String
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("realestate")
    fun getProperties():
            Call<String>
    }

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}




