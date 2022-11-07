package gst.trainingcourse.day9_savingdata_hainm18.ui.viewmodels

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.day9_savingdata_hainm18.ui.viewstates.SchoolsViewState
import gst.trainingcourse.day9_savingdata_hainm18.utils.schoolNumbers
import gst.trainingcourse.day9_savingdata_hainm18.utils.studentNumbers
import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import gst.trainingcourse.domain.usecases.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val getAllSchoolsUseCase: GetAllSchoolsUseCase,
    private val insertSchoolUseCase: InsertSchoolUseCase,
    private val updateSchoolUseCase: UpdateSchoolUseCase,
    private val deleteSchoolUseCase: DeleteSchoolUseCase,
    private val insertStudentUseCase: InsertStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase
) : BaseViewModel<SchoolsViewState>() {
    private val _schoolsState = MutableStateFlow(Pair(0, 0))
    val schoolsState: StateFlow<Pair<Int, Int>> = _schoolsState

    fun loadSchoolList() {
        viewModelScope.launch {
            getAllSchoolsUseCase()
                .catch { e ->
                    _viewState.value = SchoolsViewState.OperatorFailure(e.localizedMessage ?: "")
                }
                .collect { schools ->
                    _viewState.value = SchoolsViewState.GetSchoolSuccess(schools)
                    _schoolsState.value = Pair(schools.schoolNumbers(), schools.studentNumbers())
                }
        }
    }

    fun insertNewSchool(school: School) {
        viewModelScope.launch {
            val insertSchoolResult = insertSchoolUseCase(school)
            if (insertSchoolResult is AppResult.Error) {
                _viewState.value =
                    SchoolsViewState.OperatorFailure(insertSchoolResult.message ?: "")
            } else {
                _viewState.value = SchoolsViewState.OperatorSuccess()
            }
        }
    }

    fun updateSchool(school: School) {
        viewModelScope.launch {
            val updateSchoolResult = updateSchoolUseCase(school)
            if (updateSchoolResult is AppResult.Error) {
                _viewState.value =
                    SchoolsViewState.OperatorFailure(updateSchoolResult.message ?: "")
            } else {
                _viewState.value = SchoolsViewState.OperatorSuccess()
            }
        }
    }

    fun deleteSchool(school: School) {
        viewModelScope.launch {
            val deleteSchoolResult = deleteSchoolUseCase(school)
            if (deleteSchoolResult is AppResult.Error) {
                _viewState.value =
                    SchoolsViewState.OperatorFailure(deleteSchoolResult.message ?: "")
            }
        }
    }

    fun insertNewStudent(student: Student) {
        viewModelScope.launch {
            val insertStudentResult = insertStudentUseCase(student)
            if (insertStudentResult is AppResult.Error) {
                _viewState.value =
                    SchoolsViewState.OperatorFailure(insertStudentResult.message ?: "")
            } else {
                _viewState.value = SchoolsViewState.OperatorSuccess()
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            val updateStudentResult = updateStudentUseCase(student)
            if (updateStudentResult is AppResult.Error) {
                _viewState.value =
                    SchoolsViewState.OperatorFailure(updateStudentResult.message ?: "")
            } else {
                _viewState.value = SchoolsViewState.OperatorSuccess()
            }
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            val deleteStudentResult = deleteStudentUseCase(student)
            if (deleteStudentResult is AppResult.Error) {
                _viewState.value =
                    SchoolsViewState.OperatorFailure(deleteStudentResult.message ?: "")
            }
        }
    }
}