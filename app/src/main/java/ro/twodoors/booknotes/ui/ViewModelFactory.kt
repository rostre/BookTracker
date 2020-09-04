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
import ro.twodoors.booknotes.data.Repository
import ro.twodoors.booknotes.ui.bookdetails.BookDetailsViewModel
import ro.twodoors.booknotes.ui.books.BooksViewModel
import ro.twodoors.booknotes.ui.search.SearchViewModel
import ro.twodoors.booknotes.ui.wish.WishlistViewModel
import ro.twodoors.booknotes.ui.workdetails.WorkDetailsViewModel

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

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
                BookDetailsViewModel(application) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
