package gst.trainingcourse.day9_savingdata_hainm18.ui.adapters

import gst.trainingcourse.domain.models.School
import gst.trainingcourse.domain.models.Student

interface ClickItemListener {
    fun onCLickUpdateSchool(school: School)
    fun onClickDeleteSchool(school: School)
    fun onClickUpdateStudent(student: Student)
    fun onCLickDeleteStudent(student: Student)
}