package com.example.testapp.presentation.dialogs.choiceDialog


import android.content.res.Resources
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.DialogChoiceLanguageBinding
import com.example.testapp.presentation.dialogs.bottomSheetBase.BaseBottomSheetDialogFragment
import com.example.testapp.presentation.screens.activity.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class ChoiceLanguageDialogFragment :
    BaseBottomSheetDialogFragment<ChoiceLanguageViewModel, DialogChoiceLanguageBinding>(
        R.layout.dialog_choice_language
    ) {

    override val binding: DialogChoiceLanguageBinding by viewBinding(DialogChoiceLanguageBinding::bind)
    override val viewModel: ChoiceLanguageViewModel by viewModels()

    private lateinit var adapter: ChoiceLanguageAdapter


    override fun initialize() {
        super.initialize()

        adapter = ChoiceLanguageAdapter()

        val lang = Resources.getSystem().configuration.locales.toLanguageTags()
        adapter.setData(lang.split(",").map { it })

        binding.languageRv.adapter = adapter
        binding.languageRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupListeners() {
        super.setupListeners()
        setListener()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }

    private fun setLanguages(language: List<String>) {
        adapter.setData(language)
    }

    private fun setListener() {
        adapter.setListener(object : ChoiceLanguageVH.LanguageListener {
            override fun onClick(language: String) {
                applySelectedAppLanguage(language)
            }

        })
    }

    private fun applySelectedAppLanguage(language: String) {
        (requireActivity() as BaseActivity<*, *>).updateLocale(Locale(language.split("-")[0]))

        dismiss()
    }
}