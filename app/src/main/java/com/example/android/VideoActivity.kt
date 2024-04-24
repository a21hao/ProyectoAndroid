package com.example.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VideoActivity : AppCompatActivity() {

    private val REQUEST_VIDEO_CAPTURE = 101
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val btnStartRecording = findViewById<Button>(R.id.btn_start_recording)
        val btnStopRecording = findViewById<Button>(R.id.btn_stop_recording)

        btnStartRecording.setOnClickListener {
            dispatchTakeVideoIntent()
            btnStartRecording.isEnabled = false
            btnStopRecording.visibility = Button.VISIBLE
        }

        btnStopRecording.setOnClickListener {
            btnStartRecording.isEnabled = true
            btnStopRecording.visibility = Button.GONE
        }
    }

    private fun dispatchTakeVideoIntent() {
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (takeVideoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            val videoUri: Uri? = data?.data
            videoUri?.let {
                // Aquí puedes mostrar el vídeo grabado en un reproductor de vídeo
                val intent = Intent(Intent.ACTION_VIEW, videoUri)
                intent.setDataAndType(videoUri, "video/*")
                startActivity(intent)
            }
        }
    }
}
