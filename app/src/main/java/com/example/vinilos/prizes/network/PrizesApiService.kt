package com.example.vinilos.prizes.network

// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.RequestBody
import okhttp3.Response
// import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://back-vinilos-g6.herokuapp.com/"
enum class PrizeApiFilter(val value: String) { SHOW_MUSICIANS("musicians"), SHOW_BANDS("bands"), SHOW_ALL("all") }

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
interface PrizeApiService {

    @POST("prizes")
    suspend fun createPrize(@Body requestBody: Prize): Prize

    @GET("prizes")
    suspend fun getPrizes(): List<Prize>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object PrizesApi {
    val prizeService : PrizeApiService by lazy { retrofit.create(PrizeApiService::class.java) }
}

