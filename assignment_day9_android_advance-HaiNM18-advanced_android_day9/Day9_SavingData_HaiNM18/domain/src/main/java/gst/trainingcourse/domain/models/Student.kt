package gst.trainingcourse.domain.models

data class Student(
    val studentId: Long = 0,
    val studentName: String,
    val studentGrade: Int,
    val studentSchoolId: Long = 0
)