package id.ac.ubaya.informatika.advweek4.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.informatika.advweek4.databinding.StudentListItemBinding
import id.ac.ubaya.informatika.advweek4.util.loadImage
import id.ac.ubaya.informatika.advweek4.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class StudentDetailFragment : Fragment(),ButtonUpdateClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding
    var id = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)

        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        return dataBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        if(arguments!=null){
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            //textView.setText(id)
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id.toString())
        dataBinding.listener = this
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it

            /*textInputStudentId.setText(it.id)
            textInputStidentName.setText(it.name)
            textInputPhone.setText(it.phone)
            textInputBirthDate.setText(it.bod)
            imageView2.loadImage(it.photoUrl, progressBar3)

            var student = it
            btnNotif.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        MainActivity.showNotification(student.name.toString(), "A new notification created",R.drawable.ic_baseline_person_24)
                    }
            }*/
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(v.context, "Data Updated", Toast.LENGTH_SHORT).show()
    }


}