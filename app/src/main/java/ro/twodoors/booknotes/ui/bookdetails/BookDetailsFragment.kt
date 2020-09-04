package ro.twodoors.booknotes.ui.bookdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_book_details.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBookDetailsBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Subject
import ro.twodoors.booknotes.scaler
import ro.twodoors.booknotes.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory


class BookDetailsFragment : Fragment() {

    private lateinit var book: Book
    private lateinit var binding: FragmentBookDetailsBinding
    private val viewModel: BookDetailsViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(BookDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentBookDetailsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        book = BookDetailsFragmentArgs.fromBundle(requireArguments()).book
        binding.book = book

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
            //setTextColor(context.getColorStateList(R.color.chip_text))
        }

    private fun onSubjectsEmpty(chipGroup: ChipGroup) {
        val emptyChip = Chip(context).apply {
            text = "No subjects available"
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