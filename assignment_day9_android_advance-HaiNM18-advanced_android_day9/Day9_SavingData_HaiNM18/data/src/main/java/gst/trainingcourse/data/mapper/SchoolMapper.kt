package gst.trainingcourse.data.mapper

import gst.trainingcourse.data.entities.SchoolEntity
import gst.trainingcourse.data.entities.SchoolWithStudents
import gst.trainingcourse.data.entities.StudentEntity
import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student
import kotlinx.coroutines.flow.Flow

fun SchoolWithStudents.toSchool(): School {
    return this.run {
        School(
            school.schoolId,
            school.schoolName,
            school.schoolAddress,
            students.toStudents()
        )
    }
}

fun List<StudentEntity>.toStudents(): List<Student>? {
    return this.map { studentEntity ->
        Student(
            studentEntity.studentId,
            studentEntity.studentName,
            studentEntity.studentGrade,
            studentEntity.studentSchoolId
        )
    }
}

fun School.toSchoolEntity(): SchoolEntity {
    return this.run {
        SchoolEntity(schoolId, schoolName, schoolAddress)
    }
}

fun Student.toStudentEntity(): StudentEntity {
    return this.run {
        StudentEntity(studentId, studentName, studentGrade, studentSchoolId)
    }
}
