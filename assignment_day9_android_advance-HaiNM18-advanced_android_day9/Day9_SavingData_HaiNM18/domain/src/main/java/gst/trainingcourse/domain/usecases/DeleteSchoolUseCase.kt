package gst.trainingcourse.domain.usecases

import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.base.IParamUseCase
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.repositories.SchoolRepository
import javax.inject.Inject

class DeleteSchoolUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
): IParamUseCase<School, AppResult<Boolean>> {
    override suspend fun invoke(param: School): AppResult<Boolean> {
        return schoolRepository.deleteSchool(param)
    }
}