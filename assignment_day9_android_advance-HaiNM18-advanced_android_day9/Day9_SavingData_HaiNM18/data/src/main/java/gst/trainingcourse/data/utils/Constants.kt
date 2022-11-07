package gst.trainingcourse.data.utils

object Constants {
        object Data {
                //        Database School management
                const val DATABASE = "school_management.db"
                //        Table School
                const val SCHOOL_TABLE = "school"
                //        Table School fields
                const val SCHOOL_ID_COLUMN = "school_id"
                const val SCHOOL_NAME_COLUMN = "school_name"
                const val SCHOOL_ADDRESS_COLUMN = "school_address"

                //        Table School
                const val STUDENT_TABLE = "student"
                //        Table School fields
                const val STUDENT_ID_COLUMN = "student_id"
                const val STUDENT_NAME_COLUMN = "student_name"
                const val STUDENT_GRADE_COLUMN = "student_grade"
                const val STUDENT_SCHOOL_ID_COLUMN = "student_school_id"

                //        DEFAULT fields
                const val UNKNOWN_SCHOOL_ID = -1L
        }

        object Message {
                // Success message
                const val INSERT_STUDENT_SUCCESS = "Insert 1 student successfully."
                const val INSERT_SCHOOL_SUCCESS = "Insert 1 school successfully."

                // Failure message
                const val INSERT_STUDENT_FAILED = "Insert student failed."
                const val INSERT_SCHOOL_FAILED = "Insert school failed."
        }
}