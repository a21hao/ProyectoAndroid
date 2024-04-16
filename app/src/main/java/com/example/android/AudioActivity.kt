package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        val recordButton: Button = findViewById(R.id.recordButton)
        val stopButton: Button = findViewById(R.id.stopButton)

//        recordButton.setOnClickListener {
//            AudioRecorder.
//        }
//        stopButton.setOnClickListener {
//            stopRecording()
//        }
//        playButton.setOnClickListener {
//            playRecording()
//        }

    }
}