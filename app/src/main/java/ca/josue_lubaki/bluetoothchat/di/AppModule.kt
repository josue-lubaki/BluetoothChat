package ca.josue_lubaki.bluetoothchat.di

import android.content.Context
import ca.josue_lubaki.bluetoothchat.data.chat.AndroidBluetoothController
import ca.josue_lubaki.bluetoothchat.domain.chat.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context : Context): BluetoothController {
        return AndroidBluetoothController(context)
    }
}