package project.guessthenumber.network

import project.guessthenumber.network.remote.response.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestAPI {

    @GET("json/{query}")
    suspend fun getLocation(
        @Path("query") query: String,
        @Query("fields") fields: String? = null,
        @Query("lang") lang: String? = null,
        @Query("callback") callback: String? = null
    ): LocationResponse
}