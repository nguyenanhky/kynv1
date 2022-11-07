package gst.trainingcourse.domain.usecases

import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.base.IParamUseCase
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import gst.trainingcourse.domain.repositories.SchoolRepository
import javax.inject.Inject

class InsertStudentUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
): IParamUseCase<Student, AppResult<Boolean>> {
    override suspend fun invoke(param: Student): AppResult<Boolean> {
        val checkValidInput = isValidInput(param)
        if (checkValidInput is AppResult.Error) return checkValidInput
        return schoolRepository.insertStudent(param)
    }

    private fun isValidInput(student: Student): AppResult<Boolean> {
        return when(true) {
            student.studentName.isBlank() -> AppResult.Error(STUDENT_NAME_REQUIRED)
            student.studentGrade == -1 -> AppResult.Error(STUDENT_GRADE_REQUIRED)
            student.studentSchoolId == -1L -> AppResult.Error(STUDENT_SCHOOL_ID_REQUIRED)
            else -> AppResult.Success(true)
        }
    }

    companion object {
        const val STUDENT_NAME_REQUIRED = "Student name is required."
        const val STUDENT_GRADE_REQUIRED = "Student grade is required."
        const val STUDENT_SCHOOL_ID_REQUIRED = "Student's school Id is required."
    }
}