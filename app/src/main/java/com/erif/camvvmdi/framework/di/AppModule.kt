package com.erif.camvvmdi.framework.di

import android.content.Context
import com.erif.camvvmdi.framework.RoomNoteDataSource
import com.erif.camvvmdi.framework.UseCases
import com.erif.core.repository.NoteRepository
import com.erif.core.usecase.AddNote
import com.erif.core.usecase.GetAllNote
import com.erif.core.usecase.GetNote
import com.erif.core.usecase.GetWordCount
import com.erif.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext context: Context) = NoteRepository(RoomNoteDataSource(context))

    @Singleton
    @Provides
    fun provideUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNote(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )

}