package com.example.testapp.presentation.screens.fragments.chapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R
import com.example.testapp.databinding.FragmentChapterBinding
import com.example.testapp.presentation.Screens
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import com.github.terrakok.cicerone.Screen


class ChapterFragment : BaseFragment() {

    private lateinit var binding: FragmentChapterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChapterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goButton.setOnClickListener{
            getRouter().navigateTo(Screens.task())
        }

    }

}