package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.text_view)
        var sliderObj = findViewById<Slider>(R.id.slider)
        var textEdit = findViewById<EditText>(R.id.edit_text)
        var text: String = ""
        textEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if (!textEdit.getText().isNullOrEmpty()) {
                    var tip = textEdit.getText().toString().toBigDecimal() * (sliderObj.value / 100.0).toBigDecimal()
                    var tipAmount = tip.setScale(2, BigDecimal.ROUND_DOWN)
                    text = "Tip amount: $tipAmount"
                }
                textView.setText(text)
            }
        })
 /*       sliderObj.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
                var text: String = if (!textEdit.getText().isNullOrEmpty()) ("Bill value: ${textEdit.getText().toString().toLong()}, tip percentage: ${sliderObj.value.toInt()}%") else ("")
                textView.setText(text)
            }
            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
                var text: String = if (!textEdit.getText().isNullOrEmpty()) ("Bill value: ${textEdit.getText().toString().toLong()}, tip percentage: ${sliderObj.value.toInt()}%") else ("")
                textView.setText(text)
            }
        }) */
        sliderObj.addOnChangeListener(object: Slider.OnChangeListener {
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                // Continuous update of the value
                if (!textEdit.getText().isNullOrEmpty()) {
                    var tip = textEdit.getText().toString().toBigDecimal() * (sliderObj.value / 100.0).toBigDecimal()
                    var tipAmount = tip.setScale(2, BigDecimal.ROUND_DOWN)
                    text = "Tip amount: $tipAmount"
                }
                textView.setText(text)
            }
        })
    }
}