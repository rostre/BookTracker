package ro.twodoors.booktracker.ui.bookinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ro.twodoors.booktracker.databinding.FragmentBookInfoBinding
import ro.twodoors.booktracker.ui.ViewModelFactory

class BookInfoFragment(private val bookId: String) : Fragment() {

    private val viewModel : BookInfoViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, bookId)).get(
            BookInfoViewModel::class.java)
    }
    private lateinit var binding: FragmentBookInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookInfoBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}