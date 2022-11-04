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

package com.example.vinilos.artists.overview

//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos.artists.network.Artist
import com.example.vinilos.artists.network.ArtistsApiFilter
import com.example.vinilos.artists.network.BandsApi
import com.example.vinilos.artists.network.MusiciansApi
import kotlinx.coroutines.launch

enum class ArtistApiStatus { LOADING, ERROR, DONE }
/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ArtistApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ArtistApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of Artist
    // with new values
    private val _artists = MutableLiveData<List<Artist>>()


    // The external LiveData interface to the artist is immutable, so only this class can modify
    val artists: LiveData<List<Artist>>
        get() = _artists

    // Internally, we use a MutableLiveData to handle navigation to the selected artist
    private val _navigateToSelectedArtist = MutableLiveData<Artist?>()

    // The external immutable LiveData for the navigation artist
    val navigateToSelectedArtist: MutableLiveData<Artist?>
        get() = _navigateToSelectedArtist



    /**
     * Call getArtists() on init so we can display status immediately.
     */
    init {
        getArtists(ArtistsApiFilter.SHOW_ALL)
    }


    /**
     * Gets filtered Mars real estate artist information from the Mars API Retrofit service and
     * updates the [Artist] [List] and [ArtistApiStatus] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     * @param filter the [ArtistsApiFilter] that is sent as part of the web server request
     */
     private fun getArtists(filter: ArtistsApiFilter) {
        viewModelScope.launch {
            _status.value = ArtistApiStatus.LOADING

            try {
                if(filter.name.toString() == "SHOW_MUSICIANS"){
                    _artists.value = MusiciansApi.musiciansService.getMusicians()
                }else if (filter.name.toString() == "SHOW_BANDS"){
                    _artists.value = BandsApi.bandsService.getBands()
                }else{
                    val musicians =  MusiciansApi.musiciansService.getMusicians()
                    val bands = BandsApi.bandsService.getBands()
                    _artists.value = musicians + bands;
                }
                Log.i("DONE", _artists.value.toString());
                _status.value = ArtistApiStatus.DONE
            } catch (e: Exception) {
                Log.e("EXCEPTION", e.toString());
                _status.value = ArtistApiStatus.ERROR
                _artists.value = ArrayList()
            }
        }
    }

    /**
     */

    /**
     * When the artist is clicked, set the [_navigateToSelectedArtist] [MutableLiveData]
     * @param artist The [Artist] that was clicked on.
     */
    fun displayArtistDetails(artist: Artist) {
        _navigateToSelectedArtist.value = artist
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedArtist is set to null
     */
    fun displayArtistDetailsComplete() {
        _navigateToSelectedArtist.value = null
    }

    /**
     * Updates the data set filter for the web services by querying the data with the new filter
     * by calling [getArtists]
     * @param filter the [ArtistsApiFilter] that is sent as part of the web server request
     */
    fun updateFilter(filter: ArtistsApiFilter) {
        getArtists(filter)
    }
}