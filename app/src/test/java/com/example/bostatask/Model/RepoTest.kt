package com.example.bostatask.Model

import com.example.bostatask.Model.RemoteData.Iremote
import com.google.common.base.CharMatcher.any
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RepoTest {

    private lateinit var remoteData: Iremote
    private lateinit var repo: Repo

    @Before
    fun setUp() {
        remoteData = mockk()
        repo = Repo(remoteData)
    }

    @Test
    fun `test getCities returns expected data`() = runTest {
        // Mock response data
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
        coEvery { remoteData.getCities() } returns flowOf(ApiState.Success(mockCities))


        val result = repo.getCities().first()


        assertTrue(result is ApiState.Success)
        assertEquals(mockCities, (result as ApiState.Success).data)


        coVerify { remoteData.getCities() }
    }

    @Test
    fun `test getCities returns error`() = runTest {

        val errorMessage = "Network error"
        coEvery { remoteData.getCities() } returns flowOf(ApiState.Error(errorMessage))


        val result = repo.getCities().first()


        assertTrue(result is ApiState.Error)
        assertEquals(errorMessage, (result as ApiState.Error).message)


        coVerify { remoteData.getCities() }
    }

    @Test
    fun `test getCities returns empty list`() = runTest {

        coEvery { remoteData.getCities() } returns flowOf(ApiState.Success(emptyList()))


        val result = repo.getCities().first()


        assertTrue(result is ApiState.Success)
        assertTrue((result as ApiState.Success).data.isEmpty())


        coVerify { remoteData.getCities() }
    }

}
