package ro.twodoors.booknotes.ui.bookdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBookDetailsBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Subject
import ro.twodoors.booknotes.utils.scaler
import ro.twodoors.booknotes.utils.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.utils.initToolbar


class BookDetailsFragment : Fragment() {

    private lateinit var book: Book
    private lateinit var binding: FragmentBookDetailsBinding
    private val viewModel: BookDetailsViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, book)).get(BookDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        book = BookDetailsFragmentArgs.fromBundle(requireArguments()).book
        binding = FragmentBookDetailsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.book = book
        initToolbar(binding.toolbar, true)

        viewModel.isFavorite.observe(viewLifecycleOwner, Observer {
            if (it != null && it) {
                binding.removeFromWishlist.visibility = View.VISIBLE
                binding.addToWishlist.visibility = View.INVISIBLE
            } else {
                binding.removeFromWishlist.visibility = View.INVISIBLE
                binding.addToWishlist.visibility = View.VISIBLE
            }

        })

        viewModel.isAlreadySaved.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.removeBook.visibility = View.VISIBLE
                binding.addBook.visibility = View.INVISIBLE
            } else {
                binding.removeBook.visibility = View.INVISIBLE
                binding.addBook.visibility = View.VISIBLE
            }
        })

        setupClickListeners()
        setupSubjects()

        return binding.root
    }

    private fun setupSubjects() {
        val chipGroup = binding.chipgrpSubjects

        if (book.subjects.isNullOrEmpty()){
            onSubjectsEmpty(chipGroup)
        } else {
            displaySubjects(book.subjects, chipGroup)
        }
    }

    private fun displaySubjects(subjects: List<Subject>?, chipGroup: ChipGroup) {
        subjects?.forEach { subject ->
            val chip = createChip(subject)
            chipGroup.addView(chip)
        }
    }

    private fun createChip(subject: Subject) =
        Chip(context).apply {
            text = subject.name
            isCheckedIconVisible = false
            isClickable = false
            isCheckable = false
        }

    private fun onSubjectsEmpty(chipGroup: ChipGroup) {
        val emptyChip = Chip(context).apply {
            text = context.getString(R.string.no_subjects_available)
            isCheckable = false
            isClickable = false
        }

        chipGroup.addView(emptyChip)
    }


    private fun setupClickListeners(){
        binding.addBook.setOnClickListener {
            addBook(it)
        }

        binding.addToWishlist.setOnClickListener {
            addBookToWishlist(it)
        }

        binding.removeFromWishlist.setOnClickListener {
            removeBookFromWishlist(it)
        }

        binding.removeBook.setOnClickListener {
            removeBookFromDatabase(it)
        }
    }

    private fun removeBookFromDatabase(view: View) {
        viewModel.removeBook(book)
        view.scaler()
        this.context?.showToast("Book removed from bookshelf")
    }

    private fun removeBookFromWishlist(view: View) {
        viewModel.removeBookFromWishlist(book)
        view.scaler()
        this.context?.showToast("Book removed from bookshelf")
    }

    private fun addBook(view: View) {
        viewModel.addBook(book)
        view.scaler()
        this.context?.showToast("Book added to bookshelf")
    }

    private fun addBookToWishlist(view: View) {
        viewModel.addBookToWishlist(book)
        view.scaler()
        this.context?.showToast("Book added to wishlist")
    }

}