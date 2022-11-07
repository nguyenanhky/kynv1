package gst.trainingcourse.day9_savingdata_hainm18.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import gst.trainingcourse.day9_savingdata_hainm18.databinding.ItemSchoolSpinnerBinding
import gst.trainingcourse.domain.models.School

class SchoolSpinnerAdapter(
    context: Context, schools: List<School>
) : ArrayAdapter<School>(context, 0, schools) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = convertView?.let {
            ItemSchoolSpinnerBinding.bind(convertView)
        } ?: ItemSchoolSpinnerBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.txtSchoolName.text = (getItem(position)?.schoolName ?: "")
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }
}