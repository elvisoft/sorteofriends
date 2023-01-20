package com.example.sorteofriends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class AboutApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        val texturl=findViewById<TextView>(R.id.textUrl1)
        texturl.setMovementMethod(LinkMovementMethod.getInstance());
    }
}