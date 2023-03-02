package com.example.testapp.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.databinding.DialogAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddTaskDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogAddTaskBinding

    private var listener: AddTaskListener? = null

    fun setListener(listener: AddTaskListener){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAddTaskBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.completeCreateTask.setOnClickListener {
            listener?.onAddTaskClick(binding.taskTitle.text.toString())
            dismiss()
        }

    }

}

interface AddTaskListener{
    fun onAddTaskClick(title: String, date: Date? = null)
}