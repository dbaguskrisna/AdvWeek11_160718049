package id.ac.ubaya.informatika.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter (val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewhOLDER>()
{
    class StudentViewhOLDER(val view: View):RecyclerView.ViewHolder(view)

    fun updateStudentList(newStudentList:List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewhOLDER {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.student_list_item, parent, false)

        return StudentViewhOLDER(v)
    }

    override fun onBindViewHolder(holder: StudentViewhOLDER, position: Int) {
        holder.view.txtID.text = studentList[position].id
        holder.view.txtStudentName.text = studentList[position].name
        holder.view.imageView.loadImage(studentList[position].photoUrl, holder.view.progressBar2)
        holder.view.btnDetail.setOnClickListener {
            val id = studentList[position].id
            val action = StudentListFragmentDirections.actionStudentDetail(id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}