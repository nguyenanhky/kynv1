package gst.trainingcourse.day9_savingdata_hainm18.ui.viewstates

import gst.trainingcourse.domain.models.School

sealed class SchoolsViewState(
    val schools: List<School>? = null,
    val message: String? = null
) {
    class GetSchoolSuccess(schools: List<School>) : SchoolsViewState(schools)
    class OperatorSuccess : SchoolsViewState()
    class OperatorFailure(msg: String) : SchoolsViewState(message = msg)
}
