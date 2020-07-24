package ro.twodoors.booknotes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.twodoors.booknotes.model.OpenLibraryResponse

class RetrofitFactory {

    private val service: OpenLibraryService

    companion object {
        const val BASE_URL = "https://openlibrary.org"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(OpenLibraryService::class.java)
    }

    suspend fun searchByTitle(title: String): OpenLibraryResponse  {
        return service.searchByTitle(title)
        //return service.search(title)
    }
}