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

class PracticalTest01Var05MainActivity : AppCompatActivity() {
    private lateinit var buttonNavigateToSecondaryActivity: Button
    private lateinit var buttonTopLeft: Button
    private lateinit var buttonTopRight: Button
    private lateinit var buttonCenter: Button
    private lateinit var buttonBottomLeft: Button
    private lateinit var buttonBottomRight: Button
    private lateinit var result: TextView

    private var click = 0;

    companion object {
        private const val SECUNDARY_ACTIVITY_REQUEST_CODE = 1
    }


    private val incrementButtonClickListener = View.OnClickListener { view ->
        var newText = result.text.toString()
        if (newText.isNotEmpty() && !newText.endsWith(",")) {
            newText += ","
        }

        when (view.id) {
            R.id.texttl, R.id.texttr, R.id.buttonCenter, R.id.buttonbl, R.id.buttonbr -> {
                result.text = newText + (view as Button).text

                click = click + 1;
                gestionareService(view)
                Toast.makeText(this, click.toString(), Toast.LENGTH_SHORT).show();
            }
            R.id.navigateButton -> {

                val textToSend = result.text.toString()
                val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
                intent.putExtra("text", textToSend)
                gestionareService(view)
                startActivityForResult(intent, SECUNDARY_ACTIVITY_REQUEST_CODE)
            }

        }
    }

    fun gestionareService(view: View) {

        val text = findViewById<TextView>(R.id.text).text.toString()
        val intent = Intent(this, PracticalTest01Service::class.java).apply {
            putExtra("text", text)
        }
            startService(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_main)

        buttonNavigateToSecondaryActivity = findViewById(R.id.navigateButton)
        buttonTopLeft = findViewById(R.id.texttl)
        buttonTopRight = findViewById(R.id.texttr)
        buttonCenter = findViewById(R.id.buttonCenter)
        buttonBottomLeft = findViewById(R.id.buttonbl)
        buttonBottomRight = findViewById(R.id.buttonbr)
        result = findViewById(R.id.text)

        buttonTopLeft.setOnClickListener(incrementButtonClickListener)
        buttonTopRight.setOnClickListener(incrementButtonClickListener)
        buttonCenter.setOnClickListener(incrementButtonClickListener)
        buttonBottomLeft.setOnClickListener(incrementButtonClickListener)
        buttonBottomRight.setOnClickListener(incrementButtonClickListener)
        buttonNavigateToSecondaryActivity.setOnClickListener(incrementButtonClickListener)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString("result", result.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("result")?.let { result.text = it }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == SECUNDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode,
                Toast.LENGTH_LONG).show()
        } else {
            super.onActivityResult(requestCode, resultCode, intent)
        }
    }

    override fun onDestroy() {
        stopService(Intent(this, PracticalTest01Service::class.java))
        super.onDestroy()
    }
}
