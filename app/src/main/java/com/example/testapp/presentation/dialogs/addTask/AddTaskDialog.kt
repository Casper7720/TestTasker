package com.example.testapp.presentation.dialogs.addTask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.DialogAddTaskBinding
import com.example.testapp.presentation.NotifyWorker
import com.example.testapp.presentation.dialogs.bottomSheetBase.BaseBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class AddTaskDialog : BaseBottomSheetDialogFragment<AddTaskViewModel, DialogAddTaskBinding>(
    R.layout.dialog_add_task
) {

    override val viewModel: AddTaskViewModel by viewModels()
    override val binding: DialogAddTaskBinding by viewBinding(DialogAddTaskBinding::bind)

    private var dateAndTime: Calendar =
        Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()))


    override fun initialize() {
        super.initialize()
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.completeCreateTask.setOnClickListener {

            val delay = dateAndTime.timeInMillis - System.currentTimeMillis()

            viewModel.addTask(
                System.currentTimeMillis(),
                binding.taskTitle.text.toString(),
                dateAndTime,
                delay.toString()
            )

            val data =
                Data.Builder()
                    .putString(
                        NotifyWorker.NOTIFICATION_DESCRIPTION,
                        "Необходимо выполнить задачу"
                    )
                    .putInt(NotifyWorker.NOTIFICATION_ID,1)
                    .putString(NotifyWorker.NOTIFICATION_TITLE, binding.taskTitle.text.toString())
                    .build()

            if (delay > 0) {
                scheduleNotification(delay, data)
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

    private fun scheduleNotification(delay: Long, data: Data) {
        val notificationWork = OneTimeWorkRequest.Builder(NotifyWorker::class.java)
            .addTag(delay.toString())
            .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()

        val instanceWorkManager = WorkManager.getInstance(requireContext())
        instanceWorkManager.beginUniqueWork(
            NotifyWorker.NOTIFICATION_WORK,
            ExistingWorkPolicy.REPLACE,
            notificationWork
        ).enqueue()
    }


    private fun showDateDialog() {

        val dateListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateAndTime.set(Calendar.YEAR, year)
                dateAndTime.set(Calendar.MONTH, month)
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

    private fun showTimeDialog() {
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
