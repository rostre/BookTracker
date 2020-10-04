package ro.twodoors.booknotes.ui.viewnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ro.twodoors.booknotes.databinding.FragmentViewNotesBinding
import ro.twodoors.booknotes.utils.initToolbar

class ViewNotesFragment : Fragment() {

    private lateinit var binding: FragmentViewNotesBinding
    private val args: ViewNotesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentViewNotesBinding.inflate(layoutInflater)
        initToolbar(binding.toolbar, true)
        binding.txtNotes.text = args.book.bookNotes

        return binding.root
    }
}