package ro.twodoors.booktracker.data.remote.paging

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ro.twodoors.booktracker.data.remote.api.LibraryService
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.data.local.model.Doc
import ro.twodoors.booktracker.utils.SearchCriteria

class LibraryRepository(private val service: LibraryService) {

    fun getSearchResultStream(query: String, searchCriteria: SearchCriteria): Flow<PagingData<Doc>> {
        Log.d("LibraryRepository", "New query: $query")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                DocPagingSource(
                    service,
                    query,
                    searchCriteria
                )
            }
        ).flow
    }

    fun getBooksResultStream(doc: Doc): Flow<PagingData<Book>> {
        Log.d("Repository", doc.id)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                BookPagingSource(
                    service,
                    doc
                )
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 40
    }
}