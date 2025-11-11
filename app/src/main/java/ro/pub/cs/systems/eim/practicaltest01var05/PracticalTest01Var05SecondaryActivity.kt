//package ro.pub.cs.systems.eim.practicaltest01var05
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {
//    private lateinit var buttonVerify: Button
//    private lateinit var buttonCancel: Button
//    private lateinit var result: TextView
//
//    private val incrementButtonClickListener = View.OnClickListener { view ->
//        when(view.id) {
//            R.id.verifyButton -> {
//                val text = result.text.toString()
//                val intent = Intent()
//                intent.putExtra("result", text)
//                setResult(RESULT_OK, intent)
//                finish()
//            }
//            R.id.cancelButton -> {
//                val intent = Intent()
//                intent.putExtra("result", "")
//                setResult(RESULT_CANCELED, intent)
//                finish()
//            }
//        }
//
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_practical_test01_var05_secondary)
//
//        buttonVerify = findViewById(R.id.verifyButton)
//        buttonCancel = findViewById(R.id.cancelButton)
//        result = findViewById(R.id.textSecondary)
//
//
//        val intent = intent
//        if (intent != null && intent.hasExtra("text")) {
//            val textFromFirstActivity = intent.getStringExtra("text")
//            result.text = textFromFirstActivity
//        }
//
//        buttonVerify.setOnClickListener(incrementButtonClickListener)
//        buttonCancel.setOnClickListener(incrementButtonClickListener)
//
//
//    }
//}

package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {
    private lateinit var buttonVerify: Button
    private lateinit var buttonCancel: Button
    private lateinit var result: TextView

    private val buttonClickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.verifyButton -> {
                val text = result.text.toString()
                val intent = Intent()
                intent.putExtra("result", text)
                setResult(RESULT_OK, intent)
                finish()
            }
            R.id.cancelButton -> {
                setResult(RESULT_CANCELED, Intent())
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_secondary)

        buttonVerify = findViewById(R.id.verifyButton)
        buttonCancel = findViewById(R.id.cancelButton)
        result = findViewById(R.id.textSecondary)

        val intent = intent
        if (intent != null && intent.hasExtra("text")) {
            val textFromFirstActivity = intent.getStringExtra("text")
            result.text = textFromFirstActivity
        }

        buttonVerify.setOnClickListener(buttonClickListener)
        buttonCancel.setOnClickListener(buttonClickListener)
    }
}

