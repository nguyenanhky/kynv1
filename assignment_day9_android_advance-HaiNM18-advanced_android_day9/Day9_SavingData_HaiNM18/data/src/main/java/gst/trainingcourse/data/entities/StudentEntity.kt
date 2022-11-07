package gst.trainingcourse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import gst.trainingcourse.data.utils.Constants

@Entity(
    tableName = Constants.Data.STUDENT_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = SchoolEntity::class,
            parentColumns = [Constants.Data.SCHOOL_ID_COLUMN],
            childColumns = [Constants.Data.STUDENT_SCHOOL_ID_COLUMN],
            onDelete = CASCADE
        )
    ]
)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.Data.STUDENT_ID_COLUMN)
    val studentId: Long,
    @ColumnInfo(name = Constants.Data.STUDENT_NAME_COLUMN)
    val studentName: String,
    @ColumnInfo(name = Constants.Data.STUDENT_GRADE_COLUMN)
    val studentGrade: Int,
    @ColumnInfo(name = Constants.Data.STUDENT_SCHOOL_ID_COLUMN)
    val studentSchoolId: Long = Constants.Data.UNKNOWN_SCHOOL_ID
)
