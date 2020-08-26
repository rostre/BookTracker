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

package ro.twodoors.booknotes

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.data.Repository
import ro.twodoors.booknotes.ui.ViewModelFactory

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
//object Injection {
//
//    private fun provideRepository(): Repository {
//        return Repository(LibraryService.create())
//    }
//
//    /**
//     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
//     * [ViewModel] objects.
//     */
//    fun provideViewModelFactory(): ViewModelProvider.Factory {
//        return ViewModelFactory(provideRepository())
//    }
//}
