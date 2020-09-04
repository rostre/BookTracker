package ro.twodoors.booknotes.ui.wish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.wishlist_item.*
import ro.twodoors.booknotes.databinding.FragmentWishlistBinding
import ro.twodoors.booknotes.fader
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.scaler
import ro.twodoors.booknotes.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding
    private val viewModel: WishlistViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(WishlistViewModel::class.java)
    }
    private val adapter = WishlistAdapter {  view, book -> adapterOnClick(view, book ) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(layoutInflater)
        binding.wishlistBooks.adapter = adapter

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.wishlistBooks.addItemDecoration(decoration)

        viewModel.allBooksFromWishlist.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }


    private fun adapterOnClick(view: View, book: Book) {
        when(view.id){
            removeFromWish.id -> removeFromWishlist(view, book)
            addToBooks.id -> addToMyBooks(view, book)
        }
    }

    private fun addToMyBooks(view: View, book: Book) {
        view.scaler()
        view.fader()
        viewModel.addToBooks(book)
        this.context?.showToast("Book moved")
    }

    private fun removeFromWishlist(view: View, book: Book) {
        view.scaler()
        view.fader()
        viewModel.removeBookFromWishlist(book)
        this.context?.showToast("Book removed")
    }

}
