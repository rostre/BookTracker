package ro.twodoors.booktracker.ui.booknotes

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
import kotlinx.android.synthetic.main.fragment_book_notes.*
import ro.twodoors.booktracker.R
import ro.twodoors.booktracker.databinding.FragmentBookNotesBinding
import ro.twodoors.booktracker.ui.ViewModelFactory
import ro.twodoors.booktracker.ui.bookdetails.BookDetailsFragmentDirections
import ro.twodoors.booktracker.utils.ReadingStatus

class BookNotesFragment(val bookId: String) : Fragment() {

    private val viewModel : BookNotesViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, bookId)).get(
            BookNotesViewModel::class.java)
    }
    private lateinit var binding: FragmentBookNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentBookNotesBinding.inflate(layoutInflater)
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
        builder.setTitle(getString(R.string.choose_reading_status))
        val selectedItem = statuses.indexOf(viewModel.book.value?.readingStatus?.name)
        builder.setSingleChoiceItems(statuses, selectedItem) { dialog, which ->
            val status = ReadingStatus.valueOf(statuses[which])
            txtStatus.text = status.name
            dialog.dismiss()
        }
        builder.setNeutralButton(getString(R.string.cancel)) {
                dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun navigateToNotesDetail() {
        viewModel.navigateToNotesDetail.observe(viewLifecycleOwner, Observer { book ->
            book?.let {
                this.findNavController()
                    .navigate(BookDetailsFragmentDirections.actionBookNotesFragmentToAddBookNotesFragment(it))
                viewModel.onNotesClickedNavigated()
            }
        })
    }

    private fun updateBook(){
        viewModel.updateBook()
        findNavController().navigateUp()
    }

}