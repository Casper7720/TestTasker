package com.example.testapp.presentation.screens.fragments.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R
import com.example.testapp.databinding.FragmentTaskBinding
import com.example.testapp.presentation.Screens
import com.example.testapp.presentation.screens.fragments.base.BaseFragment

class TaskFragment : BaseFragment() {

    private lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goChapter.setOnClickListener{
            getRouter().navigateTo(Screens.chapter())
        }

    }
}