package com.example.peaceofmind.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import com.example.peaceofmind.R

class MeditateFragment : Fragment() {
    private var mediaPlayer:MediaPlayer? = null
    private var seekBar:SeekBar? = null
    private var handler = Handler()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meditate, container, false)

        val YoutubeButton = view.findViewById<Button>(R.id.YoutubeButton)
        val image = view.findViewById<ImageView>(R.id.ImageLink)
        val startButton1 = view.findViewById<Button>(R.id.firstAudioBtn)

        YoutubeButton.setOnClickListener {

            val url = "https://youtu.be/mZxcw2rPWxU?si=X73o72NzyuYjuRTn"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        image.setOnClickListener{
            val imageLink = "https://www.mindful.org/how-to-meditate/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imageLink))
            startActivity(intent)
        }
        startButton1.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this,)
                mediaPlayer?.setOnCompletionListener {
                    // Reset the SeekBar when audio playback is completed
                    seekBar?.progress = 0
                }
            }

            if (!mediaPlayer!!.isPlaying) {
                // Start audio playback
                mediaPlayer!!.start()

                // Update SeekBar progress continuously
                updateSeekBar()
            }
        }


        return view
    }


}