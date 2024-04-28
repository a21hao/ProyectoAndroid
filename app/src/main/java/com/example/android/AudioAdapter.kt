package com.example.android

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AudioAdapter(private val audioFiles: List<AudioFile>) : RecyclerView.Adapter<AudioAdapter.AudioViewHolder>() {

    private val mediaPlayerHolders: MutableList<AudioViewHolder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_audio, parent, false)
        val holder = AudioViewHolder(view)
        mediaPlayerHolders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audioFile = audioFiles[position]
        holder.bind(audioFile)
    }

    override fun getItemCount(): Int {
        return audioFiles.size
    }

    class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mediaPlayer: MediaPlayer? = null

        fun bind(audioFile: AudioFile) {
            itemView.findViewById<TextView>(R.id.textViewFileName).text = audioFile.name
            itemView.findViewById<Button>(R.id.button).setOnClickListener{
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setDataSource(audioFile.filePath)
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
        for (holder in mediaPlayerHolders) {
            holder.releaseMediaPlayer()
        }
    }
}