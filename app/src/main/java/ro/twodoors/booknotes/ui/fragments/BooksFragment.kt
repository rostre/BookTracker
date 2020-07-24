package ro.twodoors.booknotes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_books.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.BookAdapter

import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.api.RetrofitFactory

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchByTitle("Master the game")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

        private fun searchByTitle(title: String) {
        val job = Job()
        val coroutineScope = CoroutineScope(job + Dispatchers.Main)
        coroutineScope.launch {
            val resultList = RetrofitFactory().searchByTitle(title)
            bookList.adapter = BookAdapter(resultList)
            //val coverId = resultList.docs?.get(1)?.coverEditionKey
            //Picasso.get().load("https://covers.openlibrary.org/b/olid/" + coverId +"-L.jpg?default=false").into(imageView)
            Log.d("BooksFragment", "$resultList")
        }
    }

    private fun getCoverUrl(coverId: String) : String{
        return "https://covers.openlibrary.org/b/olid/" + coverId +"-L.jpg?default=false"
    }

}
