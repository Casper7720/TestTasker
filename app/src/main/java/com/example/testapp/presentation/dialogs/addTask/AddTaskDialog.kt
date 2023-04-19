package com.example.testapp.presentation.dialogs.addTask

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.DialogAddTaskBinding
import com.example.testapp.presentation.AlarmReceiver
import com.example.testapp.presentation.dialogs.bottomSheetBase.BaseBottomSheetDialogFragment
import com.example.testapp.presentation.screens.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.ZoneId
import java.util.*

@AndroidEntryPoint
class AddTaskDialog : BaseBottomSheetDialogFragment<AddTaskViewModel, DialogAddTaskBinding>(
    R.layout.dialog_add_task
) {

    override val viewModel: AddTaskViewModel by viewModels()
    override val binding: DialogAddTaskBinding by viewBinding(DialogAddTaskBinding::bind)

    private var dateAndTime: Calendar =
        GregorianCalendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()))


    override fun initialize() {
        super.initialize()
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.completeCreateTask.setOnClickListener {

            val delay = dateAndTime.timeInMillis - System.currentTimeMillis()

            viewModel.addTask(
                dateAndTime.timeInMillis,
                binding.taskTitle.text.toString(),
                dateAndTime,
                delay.toString()
            )

            if (delay > 0) {
                setAlarm()
            }

            val bundle = Bundle()
            bundle.putBoolean("add_task", true)
            setFragmentResult("REQUEST_ADD_TASK", bundle)

            dismiss()
        }

        binding.addDate.setOnClickListener {
            showDateDialog()
        }

    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }

    private fun setAlarm() {

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_ID, dateAndTime.timeInMillis)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_TITLE, binding.taskTitle.text.toString())
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_DESCRIPTION, "Необходимо выполнить задачу")

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            dateAndTime.timeInMillis.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateAndTime.timeInMillis, pendingIntent)
    }

    private fun showDateDialog() {

        val dateListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                dateAndTime.set(GregorianCalendar.YEAR, year)
                dateAndTime.set(GregorianCalendar.MONTH, month)
                dateAndTime.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth)

                showTimeDialog()
            }

        DatePickerDialog(
            requireContext(),
            dateListener,
            dateAndTime.get(GregorianCalendar.YEAR),
            dateAndTime.get(GregorianCalendar.MONTH),
            dateAndTime.get(GregorianCalendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimeDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            dateAndTime.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay)
            dateAndTime.set(GregorianCalendar.MINUTE, minute)
        }

        TimePickerDialog(
            requireContext(),
            timeListener,
            dateAndTime.get(GregorianCalendar.HOUR_OF_DAY),
            dateAndTime.get(GregorianCalendar.MINUTE),
            true
        ).show()
    }
}
