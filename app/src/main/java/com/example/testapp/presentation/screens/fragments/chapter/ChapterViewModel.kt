package com.example.testapp.presentation.screens.fragments.chapter

import com.example.testapp.domain.useCases.AddFavoriteChapterUseCase
import com.example.testapp.domain.useCases.GetFavoriteChaptersUseCase
import com.example.testapp.presentation.models.ChapterItem
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel @Inject constructor(
    private val getFavoriteChaptersUseCase: @JvmSuppressWildcards GetFavoriteChaptersUseCase,
    private val addFavoriteChapterUseCase: @JvmSuppressWildcards AddFavoriteChapterUseCase
) : BaseViewModel() {

    private val _favoriteChapterData = MutableUIStateFlow<List<ChapterItem>>()
    val favoriteChapterData = _favoriteChapterData.asStateFlow()

    private val _addFavoriteChapter = MutableUIStateFlow<Boolean>()
    val addFavoriteChapter = _addFavoriteChapter.asStateFlow()

    fun getFavoriteChapters() {
        getFavoriteChaptersUseCase().collectRequest(_favoriteChapterData) {
            it.map {
                ChapterItem(
                    name = it.name,
                    image = ""
                )
            }
        }

    }

    fun addChapter() {
        addFavoriteChapterUseCase(1, "Входящие")
            .collectRequest(_addFavoriteChapter) { it }
    }

}