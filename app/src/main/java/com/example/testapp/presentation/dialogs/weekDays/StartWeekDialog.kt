package com.example.testapp.presentation.dialogs.weekDays

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.data.models.WeekType
import com.example.testapp.databinding.DialogStartWeekBinding
import com.example.testapp.presentation.dialogs.bottomSheetBase.BaseBottomSheetDialogFragment
import com.example.testapp.presentation.models.StringItem
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class StartWeekDialog : BaseBottomSheetDialogFragment<StartWeekViewModel, DialogStartWeekBinding>(
    R.layout.dialog_start_week
) {

    override val binding: DialogStartWeekBinding by viewBinding(DialogStartWeekBinding::bind)
    override val viewModel: StartWeekViewModel by viewModels()

    private lateinit var adapter: AdapterStrings
    private var days by Delegates.notNull<Int>()

    override fun initialize() {
        super.initialize()

        days = arguments?.getInt(EXTRA) ?: 0
        adapter = AdapterStrings()

        binding.startWeekRv.adapter = adapter
        binding.startWeekRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAppInfo()
    }

    override fun setupListeners() {
        super.setupListeners()
        setAdapterListener()
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()

        viewModel.appInfo.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                val activeDay = when (days) {
                    0 -> it.startWeek
                    1 -> it.nextWeek
                    else -> it.weekend
                }
                setAdapterData(
                    WeekType.values().map { item ->
                        StringItem(
                            id = item.getType(),
                            text = getString(item.getName()),
                            isActive = activeDay == item.getType()
                        )
                    }
                )
            }
        )

        viewModel.updateStartWeek.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                viewModel.getAppInfo()
            }
        )

        viewModel.updateNextWeek.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                viewModel.getAppInfo()
            }
        )

        viewModel.updateWeekend.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                viewModel.getAppInfo()
            }
        )
    }

    private fun setAdapterData(list: List<StringItem>) {
        adapter.setData(list)
    }

    private fun setAdapterListener() {
        adapter.setListener(object : StringVH.ItemListener {
            override fun onClick(item: StringItem) {
                when (days) {
                    0 -> {
                        viewModel.updateStartWeek(item.id)
                    }
                    1 -> {
                        viewModel.updateNextWeek(item.id)
                    }
                    else -> {
                        viewModel.updateWeekend(item.id)
                    }
                }

            }

        })
    }

    companion object {

        private const val EXTRA = "extra"

        fun getNewInstance(days: Int): StartWeekDialog {
            return StartWeekDialog().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA, days)
                }
            }
        }
    }
}