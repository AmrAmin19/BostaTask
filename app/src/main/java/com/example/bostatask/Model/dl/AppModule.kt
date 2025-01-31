package com.example.bostatask.Model.dl

import com.example.bostatask.Model.Irepo
import com.example.bostatask.Model.RemoteData.ApiServices
import com.example.bostatask.Model.RemoteData.Iremote
import com.example.bostatask.Model.RemoteData.RemoteData
import com.example.bostatask.Model.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRemoteData(apiServices: ApiServices): Iremote {
        return RemoteData(apiServices) // Ensure RemoteData is set up properly
    }

    @Provides
    @Singleton
    fun provideRepo(remoteData: Iremote): Irepo {
       return Repo(remoteData)
    }

}