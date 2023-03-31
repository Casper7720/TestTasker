package com.example.testapp.presentation.screens.fragments.settings



import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentSettingsBinding
import com.example.testapp.presentation.Screens
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>(
    R.layout.fragment_settings
) {

    override val viewModel: SettingsViewModel by viewModels()
    override val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun initialize() {
        super.initialize()

        binding.toolbar.apply {
            title = getString(R.string.Settings)
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener{
                getRouter().exit()
            }
        }

    }

    override fun setupListeners() {
        super.setupListeners()

        binding.notificationButton.button.setOnClickListener {
            getRouter().navigateTo(Screens.notification())
        }
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }

}