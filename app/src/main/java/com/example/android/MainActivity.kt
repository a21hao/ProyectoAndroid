package com.example.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf("android.permission.RECORD_AUDIO"), 0)

        val menuButton : View = findViewById(R.id.addMenu)
        val videoButton : View = findViewById(R.id.fab_video)
        val audioButton : View = findViewById(R.id.fab_audio)

        videoButton.setOnClickListener {
            val intent = Intent(this@MainActivity, VideoActivity::class.java)
            startActivity(intent)
        }
        audioButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AudioActivity::class.java)
            startActivity(intent)
        }
    }
}