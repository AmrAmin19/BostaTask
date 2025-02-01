package com.example.bostatask.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.bostatask.Model.ApiState
import com.example.bostatask.Model.City
import com.example.bostatask.Model.Irepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var repo: Irepo
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repo = mockk()
        viewModel = SearchViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `test getCities success`() = runTest {

        val mockCities = listOf( City(
            cityId = "1",
            cityName = "Cairo",
            cityOtherName = "Al-Qahira",
            cityCode = "CAI",
            districts = listOf()
        ),
            City(
                cityId = "2",
                cityName = "Alexandria",
                cityOtherName = "Iskandariya",
                cityCode = "ALX",
                districts = listOf()
            ))


        coEvery { repo.getCities() } returns flowOf(ApiState.Success(mockCities))


        viewModel.getCities()


        viewModel.cities.test {
            val result = awaitItem()
            assertTrue(result is ApiState.Success)
            assertEquals(mockCities, (result as ApiState.Success).data)
        }


        coVerify { repo.getCities() }
    }


    @OptIn(ExperimentalTime::class)
    @Test
    fun `test getCities returns empty list`() = runTest {

        coEvery { repo.getCities() } returns flowOf(ApiState.Success(emptyList()))


        viewModel.getCities()


        viewModel.cities.test {
            val result = awaitItem()
            assertTrue(result is ApiState.Success)
            assertEquals(emptyList<City>(), (result as ApiState.Success).data)
        }


        coVerify { repo.getCities() }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `test getCities returns error`() = runTest {

        val errorMessage = "Network error"
        coEvery { repo.getCities() } returns flowOf(ApiState.Error(errorMessage))


        viewModel.getCities()


        viewModel.cities.test {
            val result = awaitItem()
            assertTrue(result is ApiState.Error)
            assertEquals(errorMessage, (result as ApiState.Error).message)
        }


        coVerify { repo.getCities() }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `test search filter works correctly`() = runTest {

        val cities = listOf(
            City(
                cityId = "1",
                cityName = "Cairo",
                cityOtherName = "Al-Qahira",
                cityCode = "CAI",
                districts = listOf()
            ),
            City(
                cityId = "2",
                cityName = "Alexandria",
                cityOtherName = "Iskandariya",
                cityCode = "ALX",
                districts = listOf()
            ),
            City(
                cityId = "3",
                cityName = "Aswan",
                cityOtherName = "Aswan",
                cityCode = "ASN",
                districts = listOf()
            )
        )


        coEvery { repo.getCities() } returns flowOf(ApiState.Success(cities))


        viewModel.getCities()


        viewModel.updateSearchQuery("A")


        viewModel.filteredCities.test {
            val result = awaitItem()
            assertEquals(2, result.size)
            assertEquals("Alexandria", result[0].cityName)
            assertEquals("Aswan", result[1].cityName)
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `test search filter with no results`() = runTest {

        val cities = listOf(
            City(cityId = "1", cityName = "Cairo", cityOtherName = "Al-Qahira", cityCode = "CAI", districts = listOf()),
            City(cityId = "2", cityName = "Alexandria", cityOtherName = "Iskandariya", cityCode = "ALX", districts = listOf())
        )


        coEvery { repo.getCities() } returns flowOf(ApiState.Success(cities))


        viewModel.getCities()


        viewModel.updateSearchQuery("NonExistentCity")


        viewModel.filteredCities.test {
            val result = awaitItem()
            assertTrue(result.isEmpty())
        }
    }


}
