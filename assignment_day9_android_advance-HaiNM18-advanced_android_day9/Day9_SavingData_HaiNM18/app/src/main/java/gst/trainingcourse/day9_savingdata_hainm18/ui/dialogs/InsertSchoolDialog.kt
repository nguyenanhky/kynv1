package gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs

import androidx.fragment.app.viewModels
import gst.trainingcourse.day9_savingdata_hainm18.databinding.FragmentInsertSchoolBinding
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewmodels.SchoolListViewModel
import gst.trainingcourse.domain.models.School

class InsertSchoolDialog : BaseDialogFragment<FragmentInsertSchoolBinding>() {

    private val viewModel: SchoolListViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun initAction() {
        binding.btnInsertSchool.setOnClickListener {
            viewModel.insertNewSchool(getInputSchool())
        }
    }

    private fun getInputSchool(): School = with(binding) {
        return School(
            schoolName = (editSchoolName.editText?.text ?: "").toString(),
            schoolAddress = (editSchoolAddress.editText?.text ?: "").toString()
        )
    }

    override fun getViewBinding(): FragmentInsertSchoolBinding =
        FragmentInsertSchoolBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "InsertSchoolDialog"
    }
}