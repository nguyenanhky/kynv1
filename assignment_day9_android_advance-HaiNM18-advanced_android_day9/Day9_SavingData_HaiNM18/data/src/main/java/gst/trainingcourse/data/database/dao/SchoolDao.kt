package gst.trainingcourse.data.database.dao

import androidx.room.*
import gst.trainingcourse.data.entities.SchoolEntity
import gst.trainingcourse.data.entities.SchoolWithStudents
import gst.trainingcourse.data.utils.Constants
import gst.trainingcourse.domain.models.School
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {
    @Transaction
    @Query("select * from ${Constants.Data.SCHOOL_TABLE}")
    fun getAllSchoolWithStudents(): Flow<List<SchoolWithStudents>>

    @Insert
    fun insert(school: SchoolEntity)

    @Update
    fun update(school: SchoolEntity)

    @Delete
    fun delete(school: SchoolEntity)

}