package gst.trainingcourse.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import gst.trainingcourse.data.entities.StudentEntity

@Dao
interface StudentDao {
    @Insert
    fun insert(student: StudentEntity)

    @Update
    fun update(student: StudentEntity)

    @Delete
    fun delete(student: StudentEntity)
}