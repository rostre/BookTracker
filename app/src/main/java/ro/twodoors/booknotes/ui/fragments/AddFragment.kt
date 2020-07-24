package ro.twodoors.booknotes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add.*

import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentAddBinding

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.setOnShowListener {bottomSheet ->
            val bottomSheetDialog = bottomSheet as BottomSheetDialog
            val view: View = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            BottomSheetBehavior.from(view).state = BottomSheetBehavior.STATE_EXPANDED
        }

        val addFragmentBinding = DataBindingUtil.inflate<FragmentAddBinding>(inflater, R.layout.fragment_add, container, false)

        addFragmentBinding.apply {
            lifecycleOwner = this@AddFragment
            tvSearchBook.setOnClickListener {view ->
                view.findNavController().navigate(R.id.search)
                dismiss()
            }
        }


        // Inflate the layout for this fragment
        return addFragmentBinding.root
    }

}
