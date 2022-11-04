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

package com.example.vinilos.artists.network

// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
// import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://back-vinilos-g6.herokuapp.com/"
enum class ArtistsApiFilter(val value: String) { SHOW_MUSICIANS("musicians"), SHOW_BANDS("bands"), SHOW_ALL("all") }

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface MusiciansApiService {
    /**
     * Returns a Coroutine [List] of [Artist] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "musicians" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("musicians")
    suspend fun getMusicians(): List<Artist>
}

/**
 * A public interface that exposes the [getProperties] method
 */
interface BandsApiService {
    /**
     * Returns a Coroutine [List] of [Artist] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "musicians" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("bands")
    suspend fun getBands(): List<Artist>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MusiciansApi {
    val musiciansService : MusiciansApiService by lazy { retrofit.create(MusiciansApiService::class.java) }
}
object BandsApi {
    val bandsService : BandsApiService by lazy { retrofit.create(BandsApiService::class.java) }
}
