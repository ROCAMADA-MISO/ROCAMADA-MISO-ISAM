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

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.example.vinilos.artists.overview.MarsApiStatus
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Gets Mars real estate artist information from the Mars API Retrofit service and updates the
 * [Artist] and [MarsApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 * @param filter the [ArtistsApiFilter] that is sent as part of the web server request
 */
@Parcelize
data class Artist(
        val id: String,
        val name: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "image") val imgSrcUrl: String,
        ) : Parcelable {
    //TODO: Replace or use inheritance
    val isMusician
        get() = true;
}
