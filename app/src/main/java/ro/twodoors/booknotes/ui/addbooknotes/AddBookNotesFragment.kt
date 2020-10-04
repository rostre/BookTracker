package ro.twodoors.booknotes.ui.addbooknotes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentAddBookNotesBinding
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.utils.initToolbar


class AddBookNotesFragment : Fragment() {

    private lateinit var binding: FragmentAddBookNotesBinding
    private val args: AddBookNotesFragmentArgs by navArgs()

    private val viewModel : AddBookNotesViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, args.book)).get(
            AddBookNotesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBookNotesBinding.inflate(layoutInflater)
        initToolbar(binding.toolbar, true)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_notes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save_note -> {
                 val message = viewModel.saveNote()
                 if (message != null) {
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                 } else {
                     this.findNavController().navigateUp()
                 }
            }
        }
        return true
    }
}