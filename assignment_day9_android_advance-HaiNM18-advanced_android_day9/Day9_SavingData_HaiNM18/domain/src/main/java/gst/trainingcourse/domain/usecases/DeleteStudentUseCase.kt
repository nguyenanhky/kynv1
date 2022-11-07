package gst.trainingcourse.domain.usecases

import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.base.IParamUseCase
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import gst.trainingcourse.domain.repositories.SchoolRepository
import javax.inject.Inject

class DeleteStudentUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
): IParamUseCase<Student, AppResult<Boolean>> {
    override suspend fun invoke(param: Student): AppResult<Boolean> {
        return schoolRepository.deleteStudent(param)
    }
}