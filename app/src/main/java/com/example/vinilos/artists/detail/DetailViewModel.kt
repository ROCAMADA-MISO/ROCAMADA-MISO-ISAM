/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.vinilos.artists.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.vinilos.R
import com.example.vinilos.artists.network.Artist

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [Artist].
 */
class DetailViewModel(marsProperty: Artist, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<Artist>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<Artist>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = marsProperty
    }
}
