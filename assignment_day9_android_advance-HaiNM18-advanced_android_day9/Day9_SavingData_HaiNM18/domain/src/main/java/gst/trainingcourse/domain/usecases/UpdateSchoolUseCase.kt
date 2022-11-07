package gst.trainingcourse.domain.usecases

import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.base.IParamUseCase
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import gst.trainingcourse.domain.repositories.SchoolRepository
import javax.inject.Inject

class UpdateSchoolUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
): IParamUseCase<School, AppResult<Boolean>> {
    override suspend fun invoke(param: School): AppResult<Boolean> {
        val checkValidInput = isValidInput(param)
        if (checkValidInput is AppResult.Error) return checkValidInput
        return schoolRepository.updateSchool(param)
    }

    private fun isValidInput(school: School): AppResult<Boolean> {
        return when(true) {
            school.schoolName.isBlank() -> AppResult.Error(SCHOOL_NAME_REQUIRED)
            school.schoolAddress.isBlank() -> AppResult.Error(SCHOOL_ADDRESS_REQUIRED)
            else -> AppResult.Success(true)
        }
    }

    companion object {
        const val SCHOOL_NAME_REQUIRED = "School name is required."
        const val SCHOOL_ADDRESS_REQUIRED = "School address is required."
    }
}