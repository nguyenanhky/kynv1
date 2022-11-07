package gst.trainingcourse.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import gst.trainingcourse.data.utils.Constants
import kotlinx.coroutines.flow.Flow

data class SchoolWithStudents(
    @Embedded val school: SchoolEntity,
    @Relation (
        parentColumn = Constants.Data.SCHOOL_ID_COLUMN,
        entityColumn = Constants.Data.STUDENT_SCHOOL_ID_COLUMN
    )
    val students: List<StudentEntity>
)