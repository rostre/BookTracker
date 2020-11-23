package ro.twodoors.booktracker.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.data.local.model.LibraryResponse

interface LibraryService {

    @GET("api/books?format=json&jscmd=data")
    suspend fun getBook(@Query("bibkeys") searchQuery: String): HashMap<String, Book>

    @GET("/search.json")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): LibraryResponse

    @GET("/search.json")
    suspend fun searchByAuthor(
        @Query("author") author: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): LibraryResponse

    @GET("/search.json")
    suspend fun searchByTitle(
        @Query("title") title: String,
        @Query("page") page: Int,
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