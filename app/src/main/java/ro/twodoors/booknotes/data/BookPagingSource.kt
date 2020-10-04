package ro.twodoors.booknotes.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc
import java.io.IOException

private const val STARTING_PAGE_INDEX = 0

class BookPagingSource(
    private val service: LibraryService,
    private val doc: Doc
) : PagingSource<Int, Book>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val isbns = doc.isbn ?: ArrayList()

        val query : String = if (position < isbns.size){
            isbns.get(position).let { "ISBN:$it" }
        } else {
            ""
        }
        val response = service.getBook(query)
        val books = response.values.toList()

        return try {
            LoadResult.Page(
                data = books,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position ,
                nextKey = if (books.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}