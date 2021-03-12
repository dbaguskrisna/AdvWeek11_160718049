package id.ac.ubaya.informatika.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class StudentListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = studentListAdapter

        refreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            textError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                textError.visibility = View.VISIBLE
            } else{
                textError.visibility = View.GONE
            }
        })

        viewModel.loaadingDoneLD.observe(viewLifecycleOwner, Observer {
            if(it)  {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }


}