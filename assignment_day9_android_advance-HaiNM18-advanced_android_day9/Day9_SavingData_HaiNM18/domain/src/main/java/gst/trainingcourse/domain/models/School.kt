package gst.trainingcourse.domain.models

data class School(
    val schoolId: Long = 0,
    val schoolName: String,
    val schoolAddress: String,
    val students: List<Student>? = null,
    var isExpand: Boolean = false
)