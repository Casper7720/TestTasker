package com.example.testapp.presentation.screens.fragments.dayNightNotification

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.databinding.FragmentDayNightNotificationBinding
import com.example.testapp.presentation.AlarmReceiver
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class DayNightNotificationFragment :
    BaseFragment<DayNightNotificationViewModel, FragmentDayNightNotificationBinding>(
        R.layout.fragment_day_night_notification
    ) {

    override val binding by viewBinding(FragmentDayNightNotificationBinding::bind)
    override val viewModel: DayNightNotificationViewModel by viewModels()

    private var isDay by Delegates.notNull<Boolean>()
    private lateinit var date: GregorianCalendar

    private var needUpdate = true

    private val format = SimpleDateFormat("HH:mm")

    override fun initialize() {
        super.initialize()

        isDay = arguments?.getBoolean(EXTRA_IS_DAY) ?: false
        date =
            (GregorianCalendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault())) as GregorianCalendar)


        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                getRouter().exit()
            }

            title =
                if (isDay)
                    getString(R.string.morning_summary)
                else
                    getString(R.string.night_summary)
        }

        changeButtonTime()

        binding.titleNotification.text =
            if (isDay)
                getString(R.string.morning_summary)
            else
                getString(R.string.night_summary)

        binding.descriptionNotification.text =
            if (isDay)
                getString(R.string.organize_tasks_for_tomorrow)
            else
                getString(R.string.Check_out_whats_left_to_do)

        viewModel.getNotifications()
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()

        viewModel.notifications.collectUIState(
            state = {},
            onSuccess = {
                val item = it.first { item -> item.isDay == isDay }
                needUpdate = !item.isActive
                statusNotification(item.isActive)
                setDate(item)
            },
            onError = { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        )

        viewModel.updateDate.collectUIState(
            state = {},
            onSuccess = {
                viewModel.getNotifications()
            },
            onError = {}
        )

        viewModel.updateActive.collectUIState(
            state = {},
            onSuccess = {
                viewModel.getNotifications()
            },
            onError = {}
        )
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            if (needUpdate) {
                if (isChecked) {
                    setNotification()
                } else {
                    closeNotification()
                }
            }
            needUpdate = true
        }

        binding.timeNotification.setOnClickListener {
            showTimeDialog()
        }
    }

    private fun showTimeDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            date.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay)
            date.set(GregorianCalendar.MINUTE, minute)
            changeButtonTime()
            changeNotificationDate()
        }

        TimePickerDialog(
            requireContext(),
            timeListener,
            date.get(GregorianCalendar.HOUR_OF_DAY),
            date.get(GregorianCalendar.MINUTE),
            true
        ).show()
    }

    private fun changeNotificationDate(){
        val item = viewModel.notificationList.first { item -> item.isDay == isDay }
        if(item.isActive){
            removeAlarm(item)
            setAlarm()
        }
        viewModel.updateDate(isDay, date, date.timeInMillis)
    }

    private fun setDate(item: NotificationEntity) {
        item.date?.get(GregorianCalendar.HOUR_OF_DAY)
            ?.let { date.set(GregorianCalendar.HOUR_OF_DAY, it) }

        item.date?.get(GregorianCalendar.MINUTE)
            ?.let { date.set(GregorianCalendar.MINUTE, it) }
        changeButtonTime()
    }

    private fun changeButtonTime() {
        binding.timeNotification.text = format.format(date.timeInMillis)
    }

    private fun statusNotification(isActive: Boolean) {
        binding.switchNotification.isChecked = isActive
    }

    private fun setNotification() {
        if (date.timeInMillis <= System.currentTimeMillis()) date.add(
            GregorianCalendar.DAY_OF_MONTH,
            1
        )
        viewModel.updateDate(isDay, date, date.timeInMillis)
        viewModel.updateActive(isDay, true)
        setAlarm()
    }

    private fun closeNotification() {
        val item = viewModel.notificationList.first { item -> item.isDay == isDay }
        viewModel.updateActive(isDay, false)
        removeAlarm(item)
    }

    private fun setAlarm() {

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_ID, date.timeInMillis)
        intent.putExtra(
            AlarmReceiver.ALARM_RECEIVER_TITLE,
            if (isDay) getString(R.string.morning_summary) else getString(R.string.night_summary)
        )
        intent.putExtra(
            AlarmReceiver.ALARM_RECEIVER_DESCRIPTION,
            if (isDay) getString(R.string.check_tasks) else getString(R.string.create_tasks_on_next_day)
        )

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            date.timeInMillis.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            date.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun removeAlarm(item: NotificationEntity) {
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_ID, item.dateId)
        intent.putExtra(
            AlarmReceiver.ALARM_RECEIVER_TITLE,
            if (isDay) getString(R.string.morning_summary) else getString(R.string.night_summary)
        )
        intent.putExtra(
            AlarmReceiver.ALARM_RECEIVER_DESCRIPTION,
            if (isDay) getString(R.string.check_tasks) else getString(R.string.create_tasks_on_next_day)
        )

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            item.dateId.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
    }

    companion object {

        private const val EXTRA_IS_DAY = "extra_is_day"

        fun getNewInstance(isDay: Boolean): DayNightNotificationFragment {
            return DayNightNotificationFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(EXTRA_IS_DAY, isDay)
                }
            }
        }
    }
}