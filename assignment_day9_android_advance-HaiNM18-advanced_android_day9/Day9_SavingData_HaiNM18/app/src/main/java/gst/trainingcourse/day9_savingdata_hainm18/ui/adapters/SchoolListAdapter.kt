package gst.trainingcourse.day9_savingdata_hainm18.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.day9_savingdata_hainm18.databinding.ItemSchoolBinding
import gst.trainingcourse.domain.models.School

class SchoolListAdapter(
    private val clickItemListener: ClickItemListener
) : ListAdapter<School, SchoolListAdapter.SchoolViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<School>() {
            override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem.schoolId == newItem.schoolId
            }

            override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class SchoolViewHolder(
        private val layoutManager: LinearLayoutManager,
        private val schoolBinding: ItemSchoolBinding
    ) : RecyclerView.ViewHolder(schoolBinding.root) {
        fun bind(school: School) = with(schoolBinding) {
            initView(school)
            initAction()
        }

        private fun initAction() = with(schoolBinding) {
            btnSchoolDelete.setOnClickListener {
                clickItemListener.onClickDeleteSchool(currentList[adapterPosition])
            }

            btnSchoolExpand.setOnClickListener {
                val isExpand = currentList[adapterPosition].isExpand
                currentList[adapterPosition].isExpand = !isExpand
                rcvStudentList.visibility = if (isExpand) View.GONE else View.VISIBLE
                submitList(currentList)
            }

            itemView.setOnClickListener {
                clickItemListener.onCLickUpdateSchool(currentList[adapterPosition])
            }
        }

        private fun initView(school: School) = with(schoolBinding) {
            schoolBinding.school = school
            rcvStudentList.visibility = View.GONE
            rcvStudentList.layoutManager = layoutManager

            val studentAdapter = StudentListAdapter(clickItemListener)
            schoolBinding.rcvStudentList.adapter = studentAdapter
            studentAdapter.submitList(school.students)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false),
            ItemSchoolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}