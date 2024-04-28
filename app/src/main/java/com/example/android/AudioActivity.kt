package com.example.android

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException
import android.Manifest.permission.RECORD_AUDIO
import android.content.Intent
import androidx.core.app.ActivityCompat

class AudioActivity : AppCompatActivity() {
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private var isRecording = false

    private var numAudio = 0

    private val REQUEST_MICROPHONE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        val recordButton: Button = findViewById(R.id.recordButton)
        val stopButton: Button = findViewById(R.id.stopButton)
        val playButton: Button = findViewById(R.id.playButton)

        recordButton.setOnClickListener {
            checkAudioPermission()
            start()
        }
        stopButton.setOnClickListener {
            stop()
        }
        playButton.setOnClickListener {
            play()
        }
    }

    private fun checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(this,
                RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(RECORD_AUDIO),
                REQUEST_MICROPHONE
            );
        }
    }

    private fun getFilePath(): String {
        val dir : File ?=  getExternalFilesDir(null)
        val file = File(dir, "audio$numAudio.mp3")
        numAudio++
        return file.absolutePath
    }
    private fun startMediaRecorder() {
        try {
            mediaRecorder = MediaRecorder().apply {  }
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS)
            mediaRecorder.setOutputFile(getFilePath())
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

            mediaRecorder.prepare()
            mediaRecorder.start()

        } catch (e: IOException) {
            Log.e("AudioRecorder", "startMediaRecorder() failed")
        }
    }

    private fun start() {
        if (!isRecording) {
            startMediaRecorder()
            isRecording = true
        }
        else {
            Log.d("AudioRecorder", "Already recording")
        }
    }

    private fun stop() {
        if (isRecording) {
            mediaRecorder.stop()
            mediaRecorder.release()
            isRecording = false
        }
        else
            Log.d("AudioRecorder", "Already stopped")
    }

    private fun play() {
        val filePath = getFilePath()
        val audioFile = File(filePath)
        if (!isRecording && audioFile.exists()) {
            try {
                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(filePath)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                Log.e("AudioRecorder", "play() failed")
            }
        }
        else {
            Log.e("AudioRecorder", "The app is recording!!")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        mediaRecorder.release()
    }
}