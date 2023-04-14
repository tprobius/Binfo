package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.api.BinfoApi
import com.tprobius.binformation.data.entities.Bank
import com.tprobius.binformation.data.entities.Binfo
import com.tprobius.binformation.data.entities.Country
import com.tprobius.binformation.data.entities.Number
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class BinfoApiRepositoryTest {
    private val api: BinfoApi = mock()
    private val repository = BinfoApiRepository(api)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get EXPECTED binfo`() = runTest {
        val expectedBinfo = Binfo(
            Bank("Hjørring", "Jyske Bank", "+4589893300", "www.jyskebank.dk"),
            "Visa/Dankort",
            Country("DK", "DKK", "🇩🇰", 56, 10, "Denmark", "208"),
            Number(16, true),
            false,
            "visa",
            "debit"
        )

        whenever(api.getBinfo(45717360)) doReturn expectedBinfo

        val actualBinfo = repository.getBinfo(45717360)

        assertEquals(expectedBinfo, actualBinfo)
    }
}