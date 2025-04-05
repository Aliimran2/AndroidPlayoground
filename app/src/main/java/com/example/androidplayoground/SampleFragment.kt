package com.example.androidplayoground

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidplayoground.databinding.FragmentSampleBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val viewModel: ViewModelPlayGround by viewModels()

    private var _binding: FragmentSampleBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSampleBinding.bind(view)



        viewModel.startDownload()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.downloadProgress.collect {progress ->
                binding.progressbar.progress = progress
                binding.textView.text = "$progress%"

            }
        }
    }

}