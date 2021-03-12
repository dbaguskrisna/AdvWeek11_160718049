package id.ac.ubaya.informatika.advweek4.viewmodel

import android.app.Application
import android.app.DownloadManager
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

class ListViewModel(application: Application):AndroidViewModel(application) {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loaadingDoneLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue:RequestQueue ?= null

    fun refresh() {
        loaadingDoneLD.value = true
        loadingErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringReqeust = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object :  TypeToken<List<Student>>() {}.type
                    val result = Gson().fromJson<List<Student>>(response,sType)
                    studentsLD.value = result

                    loaadingDoneLD.value = false
                    Log.d("showvolley", result.toString())
                },
                {
                    loadingErrorLD.value = true
                    loaadingDoneLD.value = false
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