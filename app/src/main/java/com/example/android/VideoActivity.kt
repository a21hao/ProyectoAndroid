package com.example.android

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class VideoActivity : AppCompatActivity() {

    private val REQUEST_VIDEO_CAPTURE = 101
    private val PERMISSION_REQUEST_CODE = 100
    private val CAMERA_PERMISSION = Manifest.permission.CAMERA
    private lateinit var currentVideoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val btnStartRecording = findViewById<Button>(R.id.btn_start_recording)
        val btnStopRecording = findViewById<Button>(R.id.btn_stop_recording)

        btnStartRecording.setOnClickListener {
            if (checkCameraPermission()) {
                dispatchTakeVideoIntent()
                btnStartRecording.isEnabled = false
                btnStopRecording.visibility = Button.VISIBLE
            } else {
                requestCameraPermission()
            }
        }

        btnStopRecording.setOnClickListener {
            btnStartRecording.isEnabled = true
            btnStopRecording.visibility = Button.GONE
            // Guardar la grabación
            saveVideo()
        }
    }

    private fun saveVideo() {
        val file = createVideoFile()
        val videoUri = Uri.fromFile(file)
        currentVideoPath = file.absolutePath
    }

    private fun createVideoFile(): File {
        // Nombre único para el archivo de video
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        return File.createTempFile(
            "VIDEO_${timeStamp}_",
            ".mp4",
            storageDir
        ).apply {
            currentVideoPath = absolutePath
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            CAMERA_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(CAMERA_PERMISSION),
            PERMISSION_REQUEST_CODE
        )
    }

    private fun dispatchTakeVideoIntent() {
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (takeVideoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array
        <String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permiso concedido, puedes iniciar la grabación
                    dispatchTakeVideoIntent()
                } else {
                    // Permiso denegado, muestra un mensaje o realiza alguna acción adicional si es necesario
                    // Por ejemplo, puedes mostrar un mensaje al usuario indicando que necesitas el permiso de cámara para grabar video.
                    // También podrías deshabilitar la funcionalidad de grabación de video o cerrar la actividad.
                    // Aquí un ejemplo de cómo mostrar un mensaje:
                    Toast.makeText(
                        this,
                        "Permiso de cámara necesario para grabar video",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
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