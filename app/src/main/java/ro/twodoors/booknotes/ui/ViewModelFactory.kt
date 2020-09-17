/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ro.twodoors.booknotes.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.bookdetails.BookDetailsViewModel
import ro.twodoors.booknotes.ui.booknotes.BookNotesViewModel
import ro.twodoors.booknotes.ui.books.BooksViewModel
import ro.twodoors.booknotes.ui.reading.quitted.QuittedViewModel
import ro.twodoors.booknotes.ui.reading.read.ReadViewModel
import ro.twodoors.booknotes.ui.reading.reading.ReadingViewModel
import ro.twodoors.booknotes.ui.reading.unread.UnreadViewModel
import ro.twodoors.booknotes.ui.search.SearchViewModel
import ro.twodoors.booknotes.ui.wish.WishlistViewModel
import ro.twodoors.booknotes.ui.workdetails.WorkDetailsViewModel

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val application: Application, private val any: Any? = null) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(application) as T
            }
            modelClass.isAssignableFrom(BooksViewModel::class.java) -> {
                BooksViewModel(application) as T
            }
            modelClass.isAssignableFrom(WishlistViewModel::class.java) -> {
                WishlistViewModel(application) as T
            }
            modelClass.isAssignableFrom(WorkDetailsViewModel::class.java) -> {
                WorkDetailsViewModel(application) as T
            }
            modelClass.isAssignableFrom(BookDetailsViewModel::class.java) -> {
                BookDetailsViewModel(application, any as Book) as T
            }
            modelClass.isAssignableFrom(BookNotesViewModel::class.java) -> {
                BookNotesViewModel(application, any as String) as T
            }
            modelClass.isAssignableFrom(QuittedViewModel::class.java) -> {
                QuittedViewModel(application) as T
            }
            modelClass.isAssignableFrom(ReadingViewModel::class.java) -> {
                ReadingViewModel(application) as T
            }
            modelClass.isAssignableFrom(ReadViewModel::class.java) -> {
                ReadViewModel(application) as T
            }
            modelClass.isAssignableFrom(UnreadViewModel::class.java) -> {
                UnreadViewModel(application) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
