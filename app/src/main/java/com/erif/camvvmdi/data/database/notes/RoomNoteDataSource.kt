package com.erif.camvvmdi.data.database.notes

import android.content.Context
import com.erif.camvvmdi.data.database.DatabaseService
import com.erif.core.data.Note
import com.erif.core.repositories.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntity().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteEntity(NoteEntity.fromNote(note))
}