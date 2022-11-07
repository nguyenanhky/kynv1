package gst.trainingcourse.domain.repositories

import gst.trainingcourse.domain.AppResult
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getAllSchools(): Flow<List<School>>

    suspend fun insertSchool(school: School): AppResult<Boolean>

    suspend fun updateSchool(school: School): AppResult<Boolean>

    suspend fun deleteSchool(school: School): AppResult<Boolean>

    suspend fun insertStudent(student: Student): AppResult<Boolean>

    suspend fun updateStudent(student: Student): AppResult<Boolean>

    suspend fun deleteStudent(student: Student): AppResult<Boolean>
}