package com.tprobius.binformation.di

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.tprobius.binformation.data.api.ApiConstants
import com.tprobius.binformation.data.api.BinformationApi
import com.tprobius.binformation.data.data_source.BinformationDatabase
import com.tprobius.binformation.data.repository.BinformationDatabaseRepository
import com.tprobius.binformation.domain.repository.BinformationRepository
import com.tprobius.binformation.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
object BinformationAppModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): BinformationApi {
        return builder
            .build()
            .create(BinformationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val logging = HttpLoggingInterceptor()
        logging.setLevel((HttpLoggingInterceptor.Level.BODY))
        val client = getUnsafeOkHttpClient()
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): BinformationDatabase {
        return Room.databaseBuilder(
            app,
            BinformationDatabase::class.java,
            BinformationDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: BinformationDatabase): BinformationRepository {
        return BinformationDatabaseRepository(db.binformationDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: BinformationRepository): BinformationUseCases {
        return BinformationUseCases(
            insertNumber = InsertNumber(repository),
            getNumbers = GetNumbers(repository),
            getNumber = GetNumber(repository),
            deleteNumber = DeleteNumber(repository)
        )
    }
}

@SuppressLint("TrustAllX509TrustManager")
private fun getUnsafeOkHttpClient(): OkHttpClient {
    val trustAllCerts = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
    object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    val sslSocketFactory = sslContext.socketFactory

    return OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }.build()
}