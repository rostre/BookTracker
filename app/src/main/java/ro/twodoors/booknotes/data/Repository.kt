package ro.twodoors.booknotes.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.model.SearchCriteria

class Repository(private val service: LibraryService) {

    fun getSearchResultStream(query: String, searchCriteria: SearchCriteria): Flow<PagingData<Doc>> {
        Log.d("Repository", "New query: $query")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { LibraryPagingSource(service, query, searchCriteria ) }
        ).flow
    }

    fun getBooksResultStream(doc: Doc): Flow<PagingData<Book>> {
        Log.d("Repository", doc.id)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { BookPagingSource(service, doc ) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 40
    }
}