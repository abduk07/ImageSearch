package kg.varis.imagesearch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("q") keyWord: String,
        @Query("key") key: String = "38041305-00db311a60691e01d96d9e57f",
        @Query("per-page") perPage: Int = 3,
        @Query("page") page: Int
    ): Call<PixaModel>
}