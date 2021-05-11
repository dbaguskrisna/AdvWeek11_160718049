package id.ac.ubaya.informatika.advweek4.view

import android.app.NotificationChannel
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class MainActivity : AppCompatActivity() {
    init{
        instace = this
    }

    companion object {
        private var instace:MainActivity ?=null

        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instace?.packageName}-${instace?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instace!!.applicationContext, channelId)
                    .apply {
                        setSmallIcon(icon)
                        setContentTitle(title)
                        setContentText(content)
                        setStyle(NotificationCompat.BigTextStyle())
                        priority = NotificationCompat.PRIORITY_DEFAULT
                        setAutoCancel(true)
                    }
            val notif = NotificationManagerCompat.from(instace!!.applicationContext)
            notif.notify(1001,builder.build())
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true, getString(R.string.app_name), "App Chanel")
    }
}