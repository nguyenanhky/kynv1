package gst.trainingcourse.domain.usecases

import gst.trainingcourse.domain.base.IUseCase
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.repositories.SchoolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSchoolsUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
): IUseCase<Flow<List<School>>> {
    override fun invoke(): Flow<List<School>>
    = schoolRepository.getAllSchools()
}