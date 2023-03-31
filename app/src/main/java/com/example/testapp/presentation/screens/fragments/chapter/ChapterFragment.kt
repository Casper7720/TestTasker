package com.example.testapp.presentation.screens.fragments.chapter

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentChapterBinding
import com.example.testapp.presentation.Screens
import com.example.testapp.presentation.models.ChapterItem
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import com.example.testapp.presentation.screens.fragments.chapter.adapter.ChapterAdapter
import com.example.testapp.presentation.screens.fragments.chapter.adapter.ChapterItemDU
import com.example.testapp.presentation.screens.fragments.chapter.adapter.ChapterVH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterFragment : BaseFragment<ChapterViewModel, FragmentChapterBinding>(
    R.layout.fragment_chapter
) {

    override val viewModel: ChapterViewModel by viewModels()
    override val binding: FragmentChapterBinding by viewBinding(FragmentChapterBinding::bind)

    private lateinit var favoriteChapterAdapter: ChapterAdapter


    override fun initialize() {
        super.initialize()

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_done)
            title = "Tasker"
            setNavigationOnClickListener {
                viewModel.addChapter()
            }
            inflateMenu(R.menu.menu_chapter)
        }

        viewModel.getFavoriteChapters()

        favoriteChapterAdapter = ChapterAdapter()




        binding.chaptersRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteChapterAdapter
        }

    }

    override fun setupListeners() {
        super.setupListeners()

        favoriteChapterAdapter.setListener(object: ChapterVH.ChapterItemListener{
            override fun onColorClick() {
                getRouter().navigateTo(Screens.task())
            }

        })

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search -> {true}
                R.id.notification -> {true}
                R.id.settings -> {
                    getRouter().navigateTo(Screens.settings())
                    true
                }
                else -> {false}
            }
        }
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()

        viewModel.favoriteChapterData.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            },
            onSuccess = {
                setItemsFavoriteChapters(it)
            }
        )

        viewModel.addFavoriteChapter.collectUIState(
            state = {},
            onError = {
                      Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            },
            onSuccess = {
                viewModel.getFavoriteChapters()
            }
        )
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.categories.collect {
//                initCategories(it)
//            }
//        }

    }

    private fun setItemsFavoriteChapters(list:List<ChapterItem>){
        favoriteChapterAdapter.setData(list)
    }
}