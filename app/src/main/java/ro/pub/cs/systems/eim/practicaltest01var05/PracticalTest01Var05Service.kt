package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class PracticalTest01Service : Service() {

    override fun onBind(intent: Intent): IBinder {
        return TODO("Provide the return value")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("PracticalTest01Var05Service", "onStartCommand() method was invoked")
        Thread {
            while (true) {
                val intent = Intent()
                intent.action = "ro.pub.cs.systems.eim.practicaltest01var05.ACTION_1"
                intent.putExtra("text", 1)
                Log.d("PracticalTest01Service", "timestamp: ${System.currentTimeMillis()}")
                sendBroadcast(intent)
                Thread.sleep(5000)
            }
        }.start()
        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}