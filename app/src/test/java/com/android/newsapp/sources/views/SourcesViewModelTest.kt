package com.android.newsapp.sources.views

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.newsapp.MainCoroutineRule
import com.android.newsapp.sources.domain.NewsSources
import com.android.newsapp.sources.network.Source
import com.android.newsapp.sources.network.SourcesResponse
import com.android.newsapp.sources.repo.ISourceRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SourcesViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sourcesViewModel: SourcesViewModel


    @MockK
    private lateinit var repo: ISourceRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sourcesViewModel = SourcesViewModel(repo)
    }

    fun getSourceResponse(): SourcesResponse {
        return SourcesResponse(emptyList(), "ok")
    }

    fun getSuccessSourceResponse(): SourcesResponse {
        val list = mutableListOf<Source>()
        list.add(Source("", "AU", "cbdn", "123", "english", "gmdjsb ", "http://google.com"))
        return SourcesResponse(list, "ok")
    }


    @Test
    fun `test successful received  sources `() {
        coEvery { repo.getAllSources() } returns Response.success(getSourceResponse())
        sourcesViewModel.getSources()
        sourcesViewModel.sourceLiveData.observeForever {}
        assert(sourcesViewModel.sourceLiveData.value is com.android.newsapp.utils.Resource.ResourceSuccess)
    }

    @Test
    fun `test failed to received  sources `() {
        coEvery { repo.getAllSources() } returns Response.error(
            404,
            ResponseBody.create(null, "Not found response body")
        )
        sourcesViewModel.getSources()
        sourcesViewModel.sourceLiveData.observeForever {}
        assert(sourcesViewModel.sourceLiveData.value is com.android.newsapp.utils.Resource.ResourceError)
    }


    @Test
    fun `test successful received  sources list `() {
        coEvery { repo.getAllSources() } returns Response.success(getSuccessSourceResponse())
        sourcesViewModel.getSources()
        sourcesViewModel.sourceLiveData.observeForever {}
        assert((sourcesViewModel.sourceLiveData.value as com.android.newsapp.utils.Resource.ResourceSuccess<List<NewsSources>>).data.isNotEmpty())
    }

}