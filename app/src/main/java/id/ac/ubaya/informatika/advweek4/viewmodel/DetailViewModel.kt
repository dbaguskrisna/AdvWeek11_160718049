package id.ac.ubaya.informatika.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.advweek4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    private val TAG = "volleyTag"
    private var queue:RequestQueue ?= null

    fun fetch(id:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=" + id
        Log.d("iduser", id.toString())

        val stringReqeust = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object :  TypeToken<Student>() {}.type
                    val result = Gson().fromJson<Student>(response,Student::class.java)
                    studentLD.value = result

                    Log.d("showvolleyDetail", result.toString())

                },
                {

                    Log.d("showvolley", it.toString())
                })
        stringReqeust.tag = TAG
        queue?.add(stringReqeust)


    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}