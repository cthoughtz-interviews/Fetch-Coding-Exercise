package com.simple.fetchrewardcompose.domain.usecase

import com.simple.fetchrewardcompose.domain.repository.ItemsRepository
import javax.inject.Inject

class RetrieveItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository
) {
    suspend fun retrieveItems() = itemsRepository.getItems()
}