package gst.trainingcourse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gst.trainingcourse.data.utils.Constants

@Entity(tableName = Constants.Data.SCHOOL_TABLE)
data class SchoolEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.Data.SCHOOL_ID_COLUMN)
    val schoolId: Long,
    @ColumnInfo(name = Constants.Data.SCHOOL_NAME_COLUMN)
    val schoolName: String,
    @ColumnInfo(name = Constants.Data.SCHOOL_ADDRESS_COLUMN)
    val schoolAddress: String
)
