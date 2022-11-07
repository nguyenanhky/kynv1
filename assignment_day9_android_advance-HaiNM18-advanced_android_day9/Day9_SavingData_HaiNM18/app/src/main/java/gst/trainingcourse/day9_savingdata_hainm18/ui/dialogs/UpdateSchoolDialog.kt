package gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import gst.trainingcourse.day9_savingdata_hainm18.databinding.FragmentUpdateSchoolBinding
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewmodels.SchoolListViewModel
import gst.trainingcourse.domain.models.School

class UpdateSchoolDialog(
    private val mSchool: School
) : BaseDialogFragment<FragmentUpdateSchoolBinding>() {

    private val viewModel: SchoolListViewModel by viewModels(ownerProducer = { requireParentFragment() })
    override fun getViewBinding(): FragmentUpdateSchoolBinding =
        FragmentUpdateSchoolBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

    override fun initView() {
        binding.school = mSchool
    }

    override fun initAction() {
        binding.btnUpdateSchool.setOnClickListener {
            viewModel.updateSchool(getInputSchool())
        }
    }

    private fun getInputSchool(): School = with(binding) {
        return School(
            schoolId = mSchool.schoolId,
            schoolName = (editSchoolName.editText?.text ?: "").toString(),
            schoolAddress = (editSchoolAddress.editText?.text ?: "").toString(),
        )
    }

    companion object {
        const val TAG = "UpdateSchoolDialog"
    }
}