package com.example.android

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val videoFiles: List<VideoFile>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private val videoViewHolders: MutableList<VideoViewHolder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        val holder = VideoViewHolder(view)
        videoViewHolders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoFile = videoFiles[position]
        holder.bind(videoFile)
    }

    override fun getItemCount(): Int {
        return videoFiles.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var videoView: VideoView = itemView.findViewById(R.id.videoView)
        private var mediaPlayer: MediaPlayer? = null

        fun bind(videoFile: VideoFile) {
            itemView.findViewById<TextView>(R.id.textViewFileName).text = videoFile.name
            videoView.setVideoURI(Uri.parse(videoFile.filePath))
            videoView.setOnClickListener {
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setDataSource(videoFile.filePath)
                mediaPlayer?.prepare()
                mediaPlayer?.start()
            }
        }

        fun releaseMediaPlayer() {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    fun releaseAllMediaPlayers() {
        for (holder in videoViewHolders) {
            holder.releaseMediaPlayer()
        }
    }
}