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
    private var handler = Handler()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meditate, container, false)



        val startButton1 = view.findViewById<Button>(R.id.firstAudioBtn)
        val pauseButton1 = view.findViewById<Button>(R.id.firstpauseBtn)
        val startButton2 = view.findViewById<Button>(R.id.secondAudioBtn)
        val pauseButton2 = view.findViewById<Button>(R.id.secondpauseBtn)
        val startButton3 = view.findViewById<Button>(R.id.thirdAudioBtn)
        val pauseButton3 = view.findViewById<Button>(R.id.thirdpauseBtn)
        val seekBar1 = view.findViewById<SeekBar>(R.id.firstSeekBar)
        val seekBar2 = view.findViewById<SeekBar>(R.id.secondSeekBar)
        val seekBar3 = view.findViewById<SeekBar>(R.id.thirdSeekBar)
        val BodyScanButton = view.findViewById<Button>(R.id.BodyScanLink)
        val WalkingButton = view.findViewById<Button>(R.id.WalkingLink)

        BodyScanButton.setOnClickListener {

            val url = "https://youtu.be/uqtIqCKjkuc?si=KquHj0dAB9IeAewZ"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        WalkingButton.setOnClickListener {

            val url = "https://youtu.be/aCwEwz1xU2M?si=i2w9AtFkLk2YYI3Z"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        startButton1.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.audio1) // Replace with your audio file name without extension
                mediaPlayer?.setOnCompletionListener {
                    // Reset the SeekBar when audio playback is completed
                    seekBar1?.progress = 0
                }
            }

            if (!mediaPlayer!!.isPlaying) {
                mediaPlayer!!.start()
                updateSeekBar(seekBar1)
            }
        }
        pauseButton1.setOnClickListener {
            mediaPlayer?.pause()
        }
        seekBar1?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.start()
            }
        })

        startButton2.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.audio2)
                mediaPlayer?.setOnCompletionListener {
                    seekBar2?.progress = 0
                }
            }

            if (!mediaPlayer!!.isPlaying) {
                mediaPlayer!!.start()
                updateSeekBar(seekBar2)
            }
        }
        pauseButton2.setOnClickListener {
            mediaPlayer?.pause()
        }
        seekBar2?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.start()
            }
        })

        startButton3.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.audio3)
                mediaPlayer?.setOnCompletionListener {
                    seekBar3?.progress = 0
                }
            }

            if (!mediaPlayer!!.isPlaying) {
                mediaPlayer!!.start()
                updateSeekBar(seekBar3)
            }
        }
        pauseButton3.setOnClickListener {
            mediaPlayer?.pause()
        }
        seekBar3?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.start()
            }
        })




        return view
    }
    private fun updateSeekBar(seekBar: SeekBar) {
        mediaPlayer?.let { player ->
            val currentPosition = player.currentPosition
            val totalDuration = player.duration

            seekBar?.max = totalDuration
            seekBar?.progress = currentPosition

            handler.postDelayed({ updateSeekBar(seekBar) }, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }


}