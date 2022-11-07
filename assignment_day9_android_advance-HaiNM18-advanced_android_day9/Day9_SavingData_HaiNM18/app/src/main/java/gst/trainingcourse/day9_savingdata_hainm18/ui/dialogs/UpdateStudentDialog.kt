package gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import gst.trainingcourse.day9_savingdata_hainm18.databinding.FragmentUpdateStudentBinding
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewmodels.SchoolListViewModel
import gst.trainingcourse.domain.models.Student

class UpdateStudentDialog(private val mStudent: Student) :
    BaseDialogFragment<FragmentUpdateStudentBinding>() {

    private val viewModel: SchoolListViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun getViewBinding(): FragmentUpdateStudentBinding =
        FragmentUpdateStudentBinding.inflate(layoutInflater)

    override fun initView() {
        binding.student = mStudent
    }

    override fun initAction() {
        binding.btnUpdateStudent.setOnClickListener {
            viewModel.updateStudent(getInputStudent())
        }
    }

    private fun getInputStudent(): Student = with(binding) {
        val gradeString = (editStudentGrade.editText?.text ?: "").toString()
        val grade = if (gradeString == "") -1 else gradeString.toInt()
        return Student(
            student?.studentId ?: 0,
            (editStudentName.editText?.text ?: "").toString(),
            grade,
            student?.studentSchoolId ?: 0
        )
    }

    companion object {
        const val TAG = "UpdateStudentDialog"
    }
}