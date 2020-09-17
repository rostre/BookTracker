package ro.twodoors.booknotes.ui.reading.reading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.book_status_item.*
import ro.twodoors.booknotes.databinding.FragmentReadingBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.reading.status.BookStatusAdapter
import ro.twodoors.booknotes.ui.reading.status.ReadingStatusFragmentDirections

class ReadingFragment : Fragment() {

    private lateinit var binding: FragmentReadingBinding
//    private lateinit var viewModel : ReadingViewModel

    private val viewModel: ReadingViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(ReadingViewModel::class.java)
    }

    //private val viewModel : ReadingViewModel by lazy { ViewModelProvider(this, ViewModelFactory(activity?.application!!)).get(ReadingViewModel::class.java)}

    private val adapter = BookStatusAdapter { view, book -> adapterOnCLick(view, book) }

    private fun adapterOnCLick(view: View, book: Book) {
        when(view.id){
            container.id -> viewModel.onBookClicked(book)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReadingBinding.inflate(layoutInflater)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //viewModel = ViewModelProvider(this, ViewModelFactory(activity?.application!!)).get(ReadingViewModel::class.java)
        binding.readingBooks.adapter = adapter
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.readingBooks.addItemDecoration(decoration)

        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
//
//        viewModel.books.observe(viewLifecycleOwner) { result ->
//            adapter.submitList(result)
//        }

        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
            book?.let {
                this.findNavController()
                    .navigate(ReadingStatusFragmentDirections.actionReadingToBookNotesFragment(it.id))
                viewModel.onBookClickedNavigated()
            }
        })

        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)

//        viewModel = ViewModelProvider(this, ViewModelFactory(activity?.application!!)).get(ReadingViewModel::class.java)
//        binding.readingBooks.adapter = adapter
//        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//        binding.readingBooks.addItemDecoration(decoration)
//
//        viewModel.books.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//        })
////
////        viewModel.books.observe(viewLifecycleOwner) { result ->
////            adapter.submitList(result)
////        }
//
//        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
//            book?.let {
//                this.findNavController()
//                    .navigate(ReadingStatusFragmentDirections.actionReadingToBookNotesFragment(it.id))
//                viewModel.onBookClickedNavigated()
//            }
//        })
 //   }

//    private fun removeBook(view: View, book: Book) {
//        viewModel.removeBook(book)
//        this.context?.showToast("Book removed")
//    }

}