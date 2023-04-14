package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.db.BinfoDao
import com.tprobius.binformation.domain.entities.Bin
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class BinfoDatabaseRepositoryTest {
    private val dao: BinfoDao = mock()
    private val repository = BinfoDatabaseRepository(dao)

    @Test
    fun `get all EXPECTED bins`() {
        val expectedBins = listOf(
            Bin(12345678, 1),
            Bin(23456789, 2),
            Bin(34567890, 3)
        )

        whenever(repository.getBins()) doReturn expectedBins

        val actualBins = repository.getBins()

        assertEquals(expectedBins, actualBins)
    }
}