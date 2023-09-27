package ecb.manifest.kowalski.obd_scan.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import ecb.manifest.kowalski.obd_scan.networking.WebSocketListener
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideWebSocketListener(): okhttp3.WebSocketListener = WebSocketListener()

    @Provides
    @Singleton
    fun provideWebSocketRepository(
        httpClient: OkHttpClient,
        webSocketListener: WebSocketListener,
    ): WebSocketRepository = WebSocketRepository(httpClient, webSocketListener)
}
