package ro.twodoors.booknotes.ui.booknotesdetailed

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_book_notes_detailed.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBookNotesDetailedBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.reading.status.ReadingStatus

class BookNotesDetailedFragment(val book: Book) : Fragment() {

    private lateinit var binding: FragmentBookNotesDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookNotesDetailedBinding.inflate(layoutInflater)
        binding.book = book
        setupSeekbar()

        binding.txtStatus.setOnClickListener {
            showSelectStatusDialog()
        }

        return binding.root
    }

    private fun showSelectStatusDialog() {
        val statuses = arrayOf(ReadingStatus.Reading.name, ReadingStatus.Read.name, ReadingStatus.Quitted.name, ReadingStatus.Unread.name)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose status")
        val selectedItem = statuses.indexOf(book.readingStatus?.name)
        builder.setSingleChoiceItems(statuses, selectedItem) { dialog, which ->
            val status = ReadingStatus.valueOf(statuses[which])
            //book.readingStatus = status
            txtStatus.text = status.name
            dialog.dismiss()
        }
        builder.setNeutralButton("Cancel") {
                dialog, _ ->
                dialog.dismiss()
        }
        builder.show()
        Toast.makeText(context, "${book.numberOfReadPages} , ${book.readingStatus} selected", Toast.LENGTH_LONG).show()
    }

    private fun setupSeekbar() {
        binding.seekBarPages.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //seekBar?.progress = progress
                tvPagesRead.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateBook(book: Book){

    }

}