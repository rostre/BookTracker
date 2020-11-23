package ro.twodoors.booktracker.ui.books

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_books.*
import kotlinx.android.synthetic.main.my_books_grid_item.*
import kotlinx.android.synthetic.main.my_books_item.*
import ro.twodoors.booktracker.R
import ro.twodoors.booktracker.databinding.FragmentBooksBinding
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.ui.ViewModelFactory
import ro.twodoors.booktracker.utils.*

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding
    private val viewModel: BooksViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(BooksViewModel::class.java)
    }
    private val onClick : (View, Book) -> Unit = { view, book -> adapterOnClick(view, book)}

    private fun adapterOnClick(view: View, book: Book) {
        when(view.id){
            deleteBook?.id -> removeBook(view, book)
            booksContainer?.id -> viewModel.onBookClicked(book)
            gridContainer?.id -> viewModel.onBookClicked(book)
        }
    }

    private val adapter = BookAdapter(onClick)

    private fun removeBook(view: View, book: Book) {
        view.scaler()
        viewModel.removeBook(book)
        this.context?.showToast(getString(R.string.book_removed_bookshelf))
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

        setupRecyclerView()
        setupFab()
        subscribeUi()

        return binding.root
    }


    private fun setupRecyclerView(){
        binding.myBooks.layoutManager = getLayoutManager()
        binding.myBooks.adapter = adapter
        binding.myBooks.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility == View.VISIBLE){
                    fab.hide()
                } else if (dy < 0 && fab.visibility != View.VISIBLE)
                    fab.show()
            }
        })

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.myBooks.addItemDecoration(decoration)
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            it?.findNavController()?.navigate(R.id.search)
        }
    }

    private fun subscribeUi(){
        viewModel.allBooks.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.toolbar.title = getString(R.string.bookshelf_count, it.count())
        })

        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
            book?.let {
                this.findNavController()
                    .navigate(BooksFragmentDirections.actionBooksToBookNotesFragment(it.id))
                viewModel.onBookClickedNavigated()
            }
        })
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
