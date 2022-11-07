package gst.trainingcourse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import gst.trainingcourse.data.database.dao.SchoolDao
import gst.trainingcourse.data.database.dao.StudentDao
import gst.trainingcourse.data.entities.SchoolEntity
import gst.trainingcourse.data.entities.StudentEntity

@Database(
    entities = [
        SchoolEntity::class,
        StudentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase: RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun studentDao(): StudentDao
}