package ro.twodoors.booknotes.api

import retrofit2.http.GET
import retrofit2.http.Query
import ro.twodoors.booknotes.model.OpenLibraryResponse

interface OpenLibraryService {

    @GET("/search.json")
    suspend fun search(@Query("q") query: String): OpenLibraryResponse

    @GET("/search.json")
    suspend fun searchByAuthor(@Query("author") author: String): OpenLibraryResponse

    @GET("/search.json")
    suspend fun searchByTitle(@Query("title") title: String): OpenLibraryResponse
}