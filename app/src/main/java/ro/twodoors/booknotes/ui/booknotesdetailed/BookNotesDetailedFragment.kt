package ro.twodoors.booknotes.ui.booknotesdetailed

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_book_notes_detailed.*
import ro.twodoors.booknotes.databinding.FragmentBookNotesDetailedBinding
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.booknotes.BookNotesFragmentDirections
import ro.twodoors.booknotes.utils.ReadingStatus

class BookNotesDetailedFragment(val bookId: String) : Fragment() {

    private val viewModel : BookNotesDetailedViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, bookId)).get(
            BookNotesDetailedViewModel::class.java)
    }
    private lateinit var binding: FragmentBookNotesDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookNotesDetailedBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupSeekbar()
        setupClickListeners()
        navigateToNotesDetail()

        return binding.root
    }

    private fun setupSeekbar() {
        binding.seekBarPages.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvPagesRead.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupClickListeners(){
        binding.txtStatus.setOnClickListener {
            showSelectStatusDialog()
        }

        binding.txtNotes.setOnClickListener {
            viewModel.onNotesClicked()
        }

        binding.btnUpdateBook.setOnClickListener {
            updateBook()
        }
    }

    private fun showSelectStatusDialog() {
        val statuses = arrayOf(ReadingStatus.Reading.name, ReadingStatus.Read.name, ReadingStatus.Quit.name, ReadingStatus.Unread.name)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose status")
        val selectedItem = statuses.indexOf(viewModel.book.value?.readingStatus?.name)
        builder.setSingleChoiceItems(statuses, selectedItem) { dialog, which ->
            val status = ReadingStatus.valueOf(statuses[which])
            txtStatus.text = status.name
            dialog.dismiss()
        }
        builder.setNeutralButton("Cancel") {
                dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun navigateToNotesDetail() {
        viewModel.navigateToNotesDetail.observe(viewLifecycleOwner, Observer { book ->
            book?.let {
                this.findNavController()
                    .navigate(BookNotesFragmentDirections.actionBookNotesFragmentToAddBookNotesFragment(it))
                viewModel.onNotesClickedNavigated()
            }
        })
    }

    private fun updateBook(){
        viewModel.updateBook()
        findNavController().navigateUp()
    }

}