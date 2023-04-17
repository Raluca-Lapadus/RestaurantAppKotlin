package com.example.navigation

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private var myVideoView: VideoView? = null
//    private var myMediaController: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        this.myVideoView = binding.myVideoView

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        setUpVideoPlayer()
    }

//    private fun setUpVideoPlayer() {
//        if(myMediaController == null) {
//            myMediaController = MediaController(this)
//            myMediaController!!.setAnchorView(this.myVideoView)
//        }
//        myVideoView!!.setMediaController(myMediaController)
//
//        myVideoView!!.setVideoURI(Uri.parse("android.resource://"
//        + packageName + "/" + R.raw.vikings))
//
//        myVideoView!!.requestFocus()
//        myVideoView!!.pause()
//        myVideoView!!.setOnCompletionListener {
//            Toast.makeText(applicationContext, "Video completed", Toast.LENGTH_SHORT).show()
//            true
//        }
//        myVideoView!!.setOnErrorListener{mp, what, extra ->
//            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
//            false
//        }
//    }
}