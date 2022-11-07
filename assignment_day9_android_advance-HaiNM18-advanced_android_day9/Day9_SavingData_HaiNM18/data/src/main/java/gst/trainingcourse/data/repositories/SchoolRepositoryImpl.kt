package gst.trainingcourse.data.repositories

import gst.trainingcourse.data.database.dao.SchoolDao
import gst.trainingcourse.data.database.dao.StudentDao
import gst.trainingcourse.data.di.DispatcherIO
import gst.trainingcourse.data.mapper.toSchool
import gst.trainingcourse.data.mapper.toSchoolEntity
import gst.trainingcourse.data.mapper.toStudentEntity
import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import gst.trainingcourse.domain.repositories.SchoolRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    @DispatcherIO private val dispatcher: CoroutineDispatcher,
    private val schoolDao: SchoolDao,
    private val studentDao: StudentDao
    ): SchoolRepository {
    override fun getAllSchools(): Flow<List<School>> {
        return schoolDao.getAllSchoolWithStudents()
            .map { schoolWithStudentList ->
                schoolWithStudentList
                    .map { schoolWithStudents ->
                        schoolWithStudents.toSchool()
                    }
            }.flowOn(dispatcher)
    }

    override suspend fun insertSchool(school: School): AppResult<Boolean> = withContext(dispatcher) {
        try {
            schoolDao.insert(school.toSchoolEntity())
            AppResult.Success(true)
        } catch (e: Exception) {
            AppResult.Error(e.message)
        }
    }

    override suspend fun updateSchool(school: School): AppResult<Boolean> = withContext(dispatcher) {
        try {
            schoolDao.update(school.toSchoolEntity())
            AppResult.Success(true)
        } catch (e: Exception) {
            AppResult.Error(e.message)
        }
    }

    override suspend fun deleteSchool(school: School): AppResult<Boolean> = withContext(dispatcher) {
        try {
            schoolDao.delete(school.toSchoolEntity())
            AppResult.Success(true)
        } catch (e: Exception) {
            AppResult.Error(e.message)
        }
    }

    override suspend fun insertStudent(student: Student): AppResult<Boolean> = withContext(dispatcher) {
        try {
            studentDao.insert(student.toStudentEntity())
            AppResult.Success(true)
        } catch (e: Exception) {
            AppResult.Error(e.message)
        }
    }

    override suspend fun updateStudent(student: Student): AppResult<Boolean> = withContext(dispatcher) {
        try {
            studentDao.update(student.toStudentEntity())
            AppResult.Success(true)
        } catch (e: Exception) {
            AppResult.Error(e.message)
        }
    }

    override suspend fun deleteStudent(student: Student): AppResult<Boolean> = withContext(dispatcher) {
        try {
            studentDao.delete(student.toStudentEntity())
            AppResult.Success(true)
        } catch (e: Exception) {
            AppResult.Error(e.message)
        }
    }
}