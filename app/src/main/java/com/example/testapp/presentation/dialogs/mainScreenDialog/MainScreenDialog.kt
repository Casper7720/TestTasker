package com.example.testapp.presentation.dialogs.mainScreenDialog

import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.data.models.MainScreenType
import com.example.testapp.databinding.DialogMainScreenBinding
import com.example.testapp.presentation.dialogs.bottomSheetBase.BaseBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenDialog :
    BaseBottomSheetDialogFragment<MainScreenDialogViewModel, DialogMainScreenBinding>(
        R.layout.dialog_main_screen
    ) {

    override val binding: DialogMainScreenBinding by viewBinding(DialogMainScreenBinding::bind)
    override val viewModel: MainScreenDialogViewModel by viewModels()

    override fun initialize() {
        super.initialize()

        viewModel.getAppInfo()
    }

    override fun setupRequests() {
        super.setupRequests()

        viewModel.getAppInfo.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                updateScreen(it.mainScreenType)
            }
        )


        viewModel.updateMainScreen.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                viewModel.getAppInfo()
            }
        )
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.mainItem.setOnClickListener { viewModel.updateMainScreen(MainScreenType.MAIN.getType()) }
        binding.tasksItem.setOnClickListener { viewModel.updateMainScreen(MainScreenType.TASKS.getType()) }
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }

    private fun updateScreen(mainScreenType: Int) {
        binding.mainItem.setBackgroundColor(getColor(requireContext(), R.color.white))
        binding.tasksItem.setBackgroundColor(getColor(requireContext(), R.color.white))

        when (mainScreenType) {
            0 -> {
                binding.mainItem.setBackgroundColor(getColor(requireContext(), R.color.pink))

            }
            1 -> {
                binding.tasksItem.setBackgroundColor(getColor(requireContext(), R.color.pink))

            }
        }

    }
}