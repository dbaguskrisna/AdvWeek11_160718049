package id.ac.ubaya.informatika.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.advweek4.model.Student

class ListViewModel:ViewModel() {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loaadingDoneLD = MutableLiveData<Boolean>()

    fun refresh() {
        val student1 = Student("01-0212370","Emmye","2015/01/13","4809251210","http://dummyimage.com/100x75.jpg/dddddd/000000")
        val student2 = Student("90-0663115","Carolee","2011/07/11","2289455492","http://dummyimage.com/100x75.jpg/dddddd/000000")
        val student3 = Student("79-0628631","Miof mela","2015/08/27","1498072491","http://dummyimage.com/100x75.jpg/5fa2dd/ffffff")

        studentsLD.value = arrayListOf<Student>(student1,student2,student3)
        loadingErrorLD.value = true
        loaadingDoneLD.value = false
    }
}