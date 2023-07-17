package com.android.newsapp.headlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.newsapp.MainCoroutineRule
import com.android.newsapp.headlines.network.Article
import com.android.newsapp.headlines.network.HeadlinesResponse
import com.android.newsapp.headlines.network.Source
import com.android.newsapp.headlines.repo.IHeadlinesRepo
import com.android.newsapp.headlines.views.HeadlinesViewModel
import com.android.newsapp.saved.repo.NewsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class HeadlinesViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var headlinesViewModel: HeadlinesViewModel


    @MockK
    private lateinit var networkRepo: IHeadlinesRepo

    @MockK
    private lateinit var localRepo: NewsRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        headlinesViewModel = HeadlinesViewModel(networkRepo, localRepo)
    }

    private fun getHeadlinesResponse(): HeadlinesResponse {
        val list = mutableListOf<Article>()
        list.add(Article("bbc", "vvsah", "fghj", "gjhg", Source("67", "fg"), "fghj", "fgh", "fgvh"))
        return HeadlinesResponse(list, "ok", 10)
    }

    @Test
    fun `test successful received  headlines `() {
        coEvery { networkRepo.getHeadlines("bbc-news") } returns Response.success(getHeadlinesResponse())
        headlinesViewModel.getHeadlines("bbc-news")
        headlinesViewModel.headlinesLiveData.observeForever {}
        assert(headlinesViewModel.headlinesLiveData.value is com.android.newsapp.utils.Resource.ResourceSuccess)
    }

    @Test
    fun `test failed received  headlines `() {
        coEvery { networkRepo.getHeadlines("bbc-news") } returns Response.error(
            404,
            ResponseBody.create(null, "Not found response body")
        )
        headlinesViewModel.getHeadlines("bbc-news")
        headlinesViewModel.headlinesLiveData.observeForever {}
        assert(headlinesViewModel.headlinesLiveData.value is com.android.newsapp.utils.Resource.ResourceError)
    }



}