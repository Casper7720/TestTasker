package com.example.testapp.presentation.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.databinding.DialogAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.ZoneId
import java.util.*

class AddTaskDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogAddTaskBinding

    private var listener: AddTaskListener? = null
    private var dateAndTime: Calendar =
        Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()))

    fun setListener(listener: AddTaskListener) {
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
            listener?.onAddTaskClick(binding.taskTitle.text.toString(), dateAndTime)
            dismiss()
        }

        binding.addDate.setOnClickListener {
            showDateDialog()
        }

    }


    private fun showDateDialog() {

        val dateListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateAndTime.set(Calendar.YEAR, year)
                dateAndTime.set(Calendar.MONTH, month + 1)
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                showTimeDialog()
            }

        DatePickerDialog(
            requireContext(),
            dateListener,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimeDialog(){
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            dateAndTime.set(Calendar.MINUTE, minute)
        }

        TimePickerDialog(
            requireContext(),
            timeListener,
            dateAndTime.get(Calendar.HOUR_OF_DAY),
            dateAndTime.get(Calendar.MINUTE),
            true
        ).show()
    }

}

interface AddTaskListener {
    fun onAddTaskClick(title: String, date: Calendar?)
}