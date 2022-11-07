package gst.trainingcourse.day9_savingdata_hainm18.utils

import gst.trainingcourse.domain.models.School

fun List<School>.schoolNumbers(): Int = size

fun List<School>.studentNumbers(): Int {
    if (this.isEmpty()) return 0
    return map { school ->
        school.students?.size ?: 0
    }.reduce { acc, studentNum -> acc + studentNum }
}
