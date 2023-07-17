package com.android.newsapp.saved

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.newsapp.MainCoroutineRule
import com.android.newsapp.saved.repo.NewsRepository
import com.android.newsapp.saved.view.SavedArticleViewModel
import com.android.newsapp.sources.views.SourcesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SavedArticleViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var savedViewModel: SavedArticleViewModel


    @MockK
    private lateinit var repo: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        savedViewModel = SavedArticleViewModel(repo)
    }


}