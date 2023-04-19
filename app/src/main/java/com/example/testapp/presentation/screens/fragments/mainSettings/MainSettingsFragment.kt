package com.example.testapp.presentation.screens.fragments.mainSettings

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentMainSettingsBinding
import com.example.testapp.presentation.dialogs.choiceDialog.ChoiceLanguageDialogFragment
import com.example.testapp.presentation.dialogs.mainScreenDialog.MainScreenDialog
import com.example.testapp.presentation.dialogs.weekDays.StartWeekDialog
import com.example.testapp.presentation.dialogs.timeZoneDialog.TimeZoneDialog
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.TimeZone

@AndroidEntryPoint
class MainSettingsFragment : BaseFragment<MainSettingsViewModel, FragmentMainSettingsBinding>(
    R.layout.fragment_main_settings
) {

    override val binding: FragmentMainSettingsBinding by viewBinding(FragmentMainSettingsBinding::bind)
    override val viewModel: MainSettingsViewModel by viewModels()

    override fun initialize() {
        super.initialize()

        binding.toolbar.apply {
            title = getString(R.string.main)
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                getRouter().exit()
            }
        }

        binding.dateAndTimeButton.setDescription(TimeZone.getDefault().id)

    }

    override fun setupListeners() {
        super.setupListeners()
        binding.mainScreenButton.setOnClickListener { showMainScreenDialog() }
        binding.languageButton.setOnClickListener { showLanguageDialog() }
        binding.dateAndTimeButton.setOnClickListener { showTimeZoneDialog() }
        binding.weekStartDayButton.setOnClickListener { showStartWeek() }
        binding.nextWeekButton.setOnClickListener { showNextWeek() }
        binding.weekendButton.setOnClickListener { showWeekend() }
        binding.notificationSoundButton.setOnClickListener {  }
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }

    private fun showLanguageDialog(){
        val dialog = ChoiceLanguageDialogFragment()
        dialog.show(parentFragmentManager, "language")
    }

    private fun showStartWeek(){
        val dialog = StartWeekDialog.getNewInstance(0)
        dialog.show(parentFragmentManager, "startWeek")
    }

    private fun showNextWeek(){
        val dialog = StartWeekDialog.getNewInstance(1)
        dialog.show(parentFragmentManager, "nextWeek")
    }

    private fun showWeekend(){
        val dialog = StartWeekDialog.getNewInstance(2)
        dialog.show(parentFragmentManager, "weekend")
    }

    private fun showTimeZoneDialog(){
        val dialog = TimeZoneDialog()
        dialog.show(parentFragmentManager, "timeZone")
    }

    private fun showMainScreenDialog(){
        val dialog = MainScreenDialog()
        dialog.show(parentFragmentManager, "mainScreen")
    }
}