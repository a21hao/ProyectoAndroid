package com.example.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Solicitar permisos
        ActivityCompat.requestPermissions(this, arrayOf(
            "Manifest.permission.RECORD_AUDIO",
            "Manifest.permission.READ_EXTERNAL_STORAGE",
            "Manifest.permission.WRITE_EXTERNAL_STORAGE",
            "Manifest.permission.CAMERA"
        ), 0)

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