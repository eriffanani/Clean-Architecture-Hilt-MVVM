package com.erif.camvvmdi.di

import android.content.Context
import com.erif.camvvmdi.data.database.notes.RoomNoteDataSource
import com.erif.camvvmdi.domain.NoteUseCases
import com.erif.core.repositories.NoteRepository
import com.erif.core.usecases.AddNote
import com.erif.core.usecases.GetAllNote
import com.erif.core.usecases.GetNote
import com.erif.core.usecases.GetWordCount
import com.erif.core.usecases.RemoveNote
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
    fun provideNoteUseCases(repository: NoteRepository) = NoteUseCases(
        AddNote(repository),
        GetAllNote(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )

}