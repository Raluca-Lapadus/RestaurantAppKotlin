package com.example.navigation.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.navigation.R
import com.example.navigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var myVideoView: VideoView? = null
    private var myMediaController: MediaController? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)



        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.myVideoView = _binding?.myVideoView
        setUpVideoPlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpVideoPlayer() {
        if(myMediaController == null) {
            myMediaController = MediaController(requireContext())
            myMediaController!!.setAnchorView(this.myVideoView)
        }
        myVideoView!!.setMediaController(myMediaController)

        myVideoView!!.setVideoURI(
            Uri.parse("android.resource://"
                + requireActivity().packageName + "/" + R.raw.vikings))

        myVideoView!!.requestFocus()
        myVideoView!!.pause()
        myVideoView!!.setOnCompletionListener {
            Toast.makeText(requireContext(), "Video completed", Toast.LENGTH_SHORT).show()
            true
        }
        myVideoView!!.setOnErrorListener{mp, what, extra ->
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            false
        }
    }
}