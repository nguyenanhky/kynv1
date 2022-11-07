package gst.trainingcourse.day9_savingdata_hainm18.ui.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.day9_savingdata_hainm18.databinding.FragmentSchoolListBinding
import gst.trainingcourse.day9_savingdata_hainm18.ui.adapters.ClickItemListener
import gst.trainingcourse.day9_savingdata_hainm18.ui.adapters.SchoolListAdapter
import gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs.InsertSchoolDialog
import gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs.InsertStudentDialog
import gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs.UpdateSchoolDialog
import gst.trainingcourse.day9_savingdata_hainm18.ui.dialogs.UpdateStudentDialog
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewmodels.SchoolListViewModel
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewstates.SchoolsViewState
import gst.trainingcourse.day9_savingdata_hainm18.utils.Constant.ERROR
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student

@AndroidEntryPoint
class SchoolListFragment : BaseFragment<FragmentSchoolListBinding>(), ClickItemListener {

    private val viewModel: SchoolListViewModel by viewModels(ownerProducer = { this })
    private val schoolAdapter = SchoolListAdapter(this)
    private val insertSchoolDialog = InsertSchoolDialog()
    private lateinit var insertStudentDialog: InsertStudentDialog
    private lateinit var updateSchoolDialog: UpdateSchoolDialog

    override fun getViewBinding(): FragmentSchoolListBinding =
        FragmentSchoolListBinding.inflate(layoutInflater)

    override fun observerState() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SchoolsViewState.GetSchoolSuccess -> loadSchools(state.schools)
                is SchoolsViewState.OperatorFailure -> showError(state.message)
                is SchoolsViewState.OperatorSuccess -> dismissDialog()
            }
        }
    }

    private fun dismissDialog() {
        (childFragmentManager.findFragmentByTag(InsertSchoolDialog.TAG) as? InsertSchoolDialog)?.dismiss()
        (childFragmentManager.findFragmentByTag(InsertStudentDialog.TAG) as? InsertStudentDialog)?.dismiss()
        (childFragmentManager.findFragmentByTag(UpdateSchoolDialog.TAG) as? UpdateSchoolDialog)?.dismiss()
        (childFragmentManager.findFragmentByTag(UpdateStudentDialog.TAG) as? UpdateStudentDialog)?.dismiss()
    }

    private fun showError(message: String?) {
        showDialog(ERROR, message ?: "something")
    }

    override fun initView() = with(binding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = this@SchoolListFragment.viewModel
        rcvSchool.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rcvSchool.adapter = schoolAdapter
        viewModel!!.loadSchoolList()
    }

    override fun initAction() = with(binding) {
        btnInsertSchool.setOnClickListener {
            insertSchoolDialog.show(childFragmentManager, InsertSchoolDialog.TAG)
        }

        btnInsertStudent.setOnClickListener {
            insertStudentDialog = InsertStudentDialog(schoolAdapter.currentList)
            insertStudentDialog.show(childFragmentManager, InsertStudentDialog.TAG)
        }
    }

    private fun loadSchools(schools: List<School>?) {
        schoolAdapter.submitList(schools)
        insertStudentDialog = InsertStudentDialog(schoolAdapter.currentList)
    }

    override fun onCLickUpdateSchool(school: School) {
        updateSchoolDialog = UpdateSchoolDialog(school)
        updateSchoolDialog.show(childFragmentManager, UpdateSchoolDialog.TAG)
    }

    override fun onClickDeleteSchool(school: School) {
        viewModel.deleteSchool(school)
    }

    override fun onClickUpdateStudent(student: Student) {
        UpdateStudentDialog(student).show(childFragmentManager, UpdateStudentDialog.TAG)
    }

    override fun onCLickDeleteStudent(student: Student) {
        viewModel.deleteStudent(student)
    }
}