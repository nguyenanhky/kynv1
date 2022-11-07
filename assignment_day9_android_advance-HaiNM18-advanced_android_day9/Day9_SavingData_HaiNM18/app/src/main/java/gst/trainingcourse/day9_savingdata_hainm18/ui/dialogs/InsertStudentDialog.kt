package gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs

import androidx.fragment.app.viewModels
import gst.trainingcourse.day9_savingdata_hainm18.databinding.FragmentInsertStudentBinding
import gst.trainingcourse.day9_savingdata_hainm18.ui.adapters.SchoolSpinnerAdapter
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewmodels.SchoolListViewModel
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student

class InsertStudentDialog(
    private val schools: List<School>
) : BaseDialogFragment<FragmentInsertStudentBinding>() {

    private val viewModel: SchoolListViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private var spinnerAdapter: SchoolSpinnerAdapter? = null

    override fun initView() {
        initSchoolSpinner()
    }

    private fun initSchoolSpinner() {
        spinnerAdapter = SchoolSpinnerAdapter(requireContext(), schools)
        binding.spinnerSchool.adapter = spinnerAdapter
    }

    override fun initAction() {
        binding.btnInsertStudent.setOnClickListener {
            viewModel.insertNewStudent(getInputStudent())
        }
    }

    private fun getInputStudent(): Student = with(binding) {
        val gradeString = (editStudentGrade.editText?.text ?: "").toString()
        val grade = if (!gradeString.removePrefix("-").removePrefix("+")
                .all { it in '0'..'9' } || gradeString == ""
        ) -1 else gradeString.toInt()
        val schoolId =
            if (spinnerSchool.selectedItem == null) -1 else (spinnerSchool.selectedItem as School).schoolId
        return Student(
            studentSchoolId = schoolId,
            studentName = (editStudentName.editText?.text ?: "").toString(),
            studentGrade = grade
        )
    }

    override fun getViewBinding(): FragmentInsertStudentBinding =
        FragmentInsertStudentBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "InsertStudentDialog"
    }
}