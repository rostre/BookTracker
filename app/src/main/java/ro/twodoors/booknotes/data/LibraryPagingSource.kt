package ro.twodoors.booknotes.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.SearchCriteria
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class LibraryPagingSource(
    private val service: LibraryService,
    private val query: String,
    private val searchCriteria: SearchCriteria
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val response = when(searchCriteria){
                SearchCriteria.Author -> service.searchByAuthor(apiQuery, position, params.loadSize)
                SearchCriteria.Title -> service.searchByTitle(apiQuery, position, params.loadSize)
                else -> service.search(apiQuery, position, params.loadSize)
            }

            val books = response.docs
            LoadResult.Page(
                data = books,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (books.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}