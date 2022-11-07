package gst.trainingcourse.day9_savingdata_hainm18.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.day9_savingdata_hainm18.databinding.ItemStudentBinding
import gst.trainingcourse.domain.models.Student

class StudentListAdapter(
    private val clickItemListener: ClickItemListener
) : ListAdapter<Student, StudentListAdapter.StudentViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.studentId == newItem.studentId
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class StudentViewHolder(private val studentBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(studentBinding.root) {
        fun bind(student: Student) = with(studentBinding) {
            studentBinding.student = student
            btnStudentDelete.setOnClickListener {
                clickItemListener.onCLickDeleteStudent(currentList[adapterPosition])
            }

            itemView.setOnClickListener {
                clickItemListener.onClickUpdateStudent(currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
