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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoFile = videoFiles[position]
        holder.bind(videoFile)
    }

    override fun getItemCount(): Int {
        return videoFiles.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoView: VideoView = itemView.findViewById(R.id.videoView)

        fun bind(videoFile: VideoFile) {
            // Set video URI to the VideoView
            videoView.setVideoURI(Uri.parse(videoFile.filePath))

            // Handle video playback controls (play, pause, etc.) as needed
            // You can add listeners here to control video playback
        }
    }
}
