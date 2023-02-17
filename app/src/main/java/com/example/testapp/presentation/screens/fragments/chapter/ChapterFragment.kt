package com.example.testapp.presentation.screens.fragments.chapter

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentChapterBinding
import com.example.testapp.presentation.models.ChapterItem
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import com.example.testapp.presentation.screens.fragments.task.adapter.ChapterAdapter
import com.example.testapp.presentation.screens.fragments.task.adapter.ChapterItemDU
import com.example.testapp.presentation.screens.fragments.task.adapter.ChapterVH
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
            setNavigationOnClickListener {
                viewModel.addChapter()
            }
            inflateMenu(R.menu.menu_chapter)
        }

        viewModel.getFavoriteChapters()

        favoriteChapterAdapter = ChapterAdapter()

        favoriteChapterAdapter.setListener(object: ChapterVH.ChapterItemListener{
            override fun onColorClick() {
                Toast.makeText(context, "", Toast.LENGTH_LONG).show()
            }

        })


        binding.chaptersRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteChapterAdapter
        }

    }

    override fun setupListeners() {
        super.setupListeners()
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