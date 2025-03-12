package com.simple.fetchrewardcompose

import com.simple.fetchrewardcompose.domain.model.ItemsResponse
import com.simple.fetchrewardcompose.domain.usecase.RetrieveItemsUseCase
import com.simple.fetchrewardcompose.presentation.viewmodel.ItemsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals


@ExperimentalCoroutinesApi
class ItemsViewModelTest {

    private lateinit var viewModel: ItemsViewModel
    private val itemsUseCase: RetrieveItemsUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ItemsViewModel(itemsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getItems should update itemsResultFlow with Loading and then Success`() = runBlocking {
        // Arrange
        val expectedResponse = ItemsResponse().apply {
            add(ItemsResponse.ItemsResponseItem(id = 1, listId = 111, name = "Item 1"))
            add(ItemsResponse.ItemsResponseItem(id = 2, listId = 222, name = "Item 2"))
        }

        // Stub the use case to return the expected response
        coEvery { itemsUseCase.retrieveItems() } returns FetchResult.Success(expectedResponse)

        // Act
        viewModel.getItems()

        // Assert
        assertEquals(FetchResult.Loading, viewModel.itemResultFlow.first())

        // Advance the test dispatcher to complete the coroutine
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.itemResultFlow.first()
        assertEquals(FetchResult.Success(expectedResponse), result)
    }

    @Test
    fun `getItems should update itemsResultFlow with Loading and then Error`() =  runBlocking {
        // Arrange
        val expectedError = Exception("Something went wrong")
        coEvery { itemsUseCase.retrieveItems() } returns FetchResult.Failure(expectedError.message?: "Error")


        // Act
        viewModel.getItems()

        // Assert
        assertEquals(FetchResult.Loading, viewModel.itemResultFlow.first())

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(FetchResult.Failure<ItemsResponse>(expectedError.message ?: "Error"), viewModel.itemResultFlow.first())
    }
}