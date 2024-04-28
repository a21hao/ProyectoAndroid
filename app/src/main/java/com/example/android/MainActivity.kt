package com.example.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var audioAdapter: AudioAdapter
    private val audioFilesList = mutableListOf<AudioFile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        recyclerView = findViewById(R.id.audioRecylerView)
        audioAdapter = AudioAdapter(audioFilesList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = audioAdapter

        loadAudioFiles()
    }

    override fun onResume() {
        super.onResume()
        loadAudioFiles()
    }

    private fun loadAudioFiles() {
        audioFilesList.clear()
        val dir = getExternalFilesDir(null)
        dir?.listFiles()?.forEach { file ->
            if (file.isFile && file.extension.equals("mp3", ignoreCase = true)) {
                audioFilesList.add(AudioFile(file.name, file.absolutePath))
            }
        }
        audioAdapter.notifyDataSetChanged()
        Log.d("audios", audioFilesList.size.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        audioAdapter.releaseAllMediaPlayers()
    }
}