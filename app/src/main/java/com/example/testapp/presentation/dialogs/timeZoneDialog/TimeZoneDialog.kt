package com.example.testapp.presentation.dialogs.timeZoneDialog


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.DialogTimezoneBinding
import com.example.testapp.presentation.dialogs.bottomSheetBase.BaseBottomSheetDialogFragment
import com.example.testapp.presentation.dialogs.timeZoneDialog.adapter.TimeZoneAdapter
import com.example.testapp.presentation.dialogs.timeZoneDialog.adapter.TimeZoneVH
import com.example.testapp.presentation.models.TimeZoneItem
import com.example.testapp.presentation.screens.CenterScrollLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TimeZoneDialog : BaseBottomSheetDialogFragment<TimeZoneViewModel, DialogTimezoneBinding>(
    R.layout.dialog_timezone
) {

    override val binding: DialogTimezoneBinding by viewBinding(DialogTimezoneBinding::bind)
    override val viewModel: TimeZoneViewModel by viewModels()

    private lateinit var adapter: TimeZoneAdapter

    override fun initialize() {
        super.initialize()

        adapter = TimeZoneAdapter()
        val timeZones = TimeZone.getAvailableIDs()
        var position = 0
        var smoothScroller = object : LinearSmoothScroller(requireContext()) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        position = timeZones.indexOf(TimeZone.getDefault().id)
        adapter.setData(timeZones.map {
            TimeZoneItem(
                id = System.currentTimeMillis(),
                timeZoneId = it,
                isActive = TimeZone.getDefault().id == it
            )
        })

        adapter.setListener(object : TimeZoneVH.TimeZoneListener {
            override fun onClick(id: String) {
                val timeZone = TimeZone.getTimeZone(id)
                TimeZone.setDefault(timeZone)
                position = timeZones.indexOf(id)
                adapter.setData(timeZones.map {
                    TimeZoneItem(
                        id = System.currentTimeMillis(),
                        timeZoneId = it,
                        isActive = TimeZone.getDefault().id == it
                    )
                })
                viewModel.updateTimeZoneId(id)
            }

        })


        binding.timezoneRv.adapter = adapter
        binding.timezoneRv.layoutManager = CenterScrollLayoutManager(
            requireContext(),
            orientation = LinearLayoutManager.VERTICAL,
            false
        )
        binding.timezoneRv.scrollToPosition(position)

    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }
}