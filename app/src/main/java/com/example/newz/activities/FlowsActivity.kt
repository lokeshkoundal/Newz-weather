package com.example.newz.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newz.R
import com.example.newz.viewmodels.FlowsVM
import kotlinx.coroutines.launch

class FlowsActivity : AppCompatActivity() {
    private lateinit var textiew : TextView
    private lateinit var btn : Button

    private val viewModel : FlowsVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flows)

        textiew = findViewById(R.id.textView)
        btn = findViewById(R.id.btn)

        lifecycleScope.launch {
            viewModel.numberFlow.collect{
                textiew.text = it.toString()
            }
        }

//        btn.setOnClickListener{
//
//        }

    }
}