package ro.twodoors.booknotes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LibraryService {

    @GET("/search.json")
    suspend fun search(@Query("q") query: String): LibraryResponse

    @GET("/search.json")
    suspend fun searchByAuthor(@Query("author") author: String): LibraryResponse

    @GET("/search.json")
    suspend fun searchByTitle(
        @Query("title") title: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
        ) : LibraryResponse

    companion object {
        const val BASE_URL = "https://openlibrary.org"


        fun create(): LibraryService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LibraryService::class.java)
        }
    }
}