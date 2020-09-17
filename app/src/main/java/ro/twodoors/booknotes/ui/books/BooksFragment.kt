package ro.twodoors.booknotes.ui.books

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_books.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBooksBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.scaler
import ro.twodoors.booknotes.utils.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.utils.SharedPrefsHelper
import ro.twodoors.booknotes.utils.initToolbar

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding

    private val viewModel: BooksViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(BooksViewModel::class.java)
    }
    private val onClick : (View, Book) -> Unit = { view, book -> removeBook(view, book)}
    private val adapter = BookAdapter(onClick)

    private fun removeBook(view: View, book: Book) {
        view.scaler()
        viewModel.removeBook(book)
        this.context?.showToast("Book removed")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentBooksBinding.inflate(layoutInflater)
        initToolbar(binding.toolbar)
        binding.myBooks.layoutManager = getLayoutManager()
        binding.myBooks.adapter = adapter

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.myBooks.addItemDecoration(decoration)

        viewModel.allBooks.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_type_menu, menu)

        toolbar.menu.findItem(R.id.action_view_grid)?.isVisible = SharedPrefsHelper.getItemType(requireContext()) == ItemType.LIST.name
        toolbar.menu.findItem(R.id.action_view_list)?.isVisible = SharedPrefsHelper.getItemType(requireContext()) == ItemType.GRID.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_view_list -> {
                binding.myBooks.layoutManager = LinearLayoutManager(requireContext())
                SharedPrefsHelper.setItemType(requireContext(), ItemType.LIST)
                binding.myBooks.adapter = adapter
                item.isVisible = false
                binding.toolbar.menu.findItem(R.id.action_view_grid).isVisible = true

            }
            R.id.action_view_grid -> {

                binding.myBooks.layoutManager = GridLayoutManager(requireContext(), 3)
                SharedPrefsHelper.setItemType(requireContext(), ItemType.GRID)
                binding.myBooks.adapter = adapter
                item.isVisible = false
                binding.toolbar.menu.findItem(R.id.action_view_list).isVisible = true
            }
        }
        return true
    }

    private fun getLayoutManager() : RecyclerView.LayoutManager {
        val layoutStyle = SharedPrefsHelper.getItemType(requireContext())
        return if (layoutStyle == ItemType.GRID.name) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }
    }
}
