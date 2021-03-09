package id.ac.ubaya.informatika.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.advweek4.model.Student

class DetailViewModel:ViewModel() {
    val studentLD = MutableLiveData<Student>()
    fun fetch() {
        val student1 = Student("01-0212370","Emmye","2015/01/13","4809251210","http://dummyimage.com/100x75.jpg/dddddd/000000")
        studentLD.value = student1
    }
}