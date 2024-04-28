package com.example.android

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.IOException

class AudioRecorder {
    /*private lateinit var mediaRecorder: MediaRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private var isRecording = false
    private var doesAudioExist = false

    override fun onCreate(savedInstanceState: Bundle?) {

        val startButton: Button = findViewById(R.id.startButton)
        val stopButton: Button = findViewById(R.id.stopButton)
        val playButton: Button = findViewById(R.id.playButton)

        startButton.setOnClickListener {
            start()
        }
        stopButton.setOnClickListener {
            stop()
        }
        playButton.setOnClickListener {
            play()
        }
    }

    private fun getFilePath(): String {
        val dir : File =  getExternalFilesDir(null)
        val file = File(dir, "audio.mp3")
        return file.absolutePath
        return "FIX"
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

    public fun start() {
        if (!isRecording) {
            startMediaRecorder()
            isRecording = true
        }
        else {
            Log.d("AudioRecorder", "Already recording")
        }
    }

    public fun stop() {
        if (isRecording) {
            mediaRecorder.stop()
            mediaRecorder.release()
            isRecording = false
        }
        else
            Log.d("AudioRecorder", "Already stopped")
    }

    public fun play() {
        if (!isRecording && doesAudioExist) {
            try {
                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(getFilePath())
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

    fun onDestroy() {
        mediaPlayer.release()
        mediaRecorder.release()
    }*/
}