package com.example.testapp.presentation.screens.fragments.notification

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentNotificationBinding
import com.example.testapp.presentation.Screens
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<NotificationViewModel, FragmentNotificationBinding>(
    R.layout.fragment_notification
) {
    override val viewModel: NotificationViewModel by viewModels()
    override val binding: FragmentNotificationBinding by viewBinding(FragmentNotificationBinding::bind)

    override fun initialize() {
        super.initialize()

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                getRouter().exit()
            }
            title = getString(R.string.notification_ru)
        }
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.morningButton.setOnClickListener {
            getRouter().navigateTo(Screens.dayNightNotification(true))
        }

        binding.nightButton.setOnClickListener {
            getRouter().navigateTo(Screens.dayNightNotification(false))
        }

    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }
}