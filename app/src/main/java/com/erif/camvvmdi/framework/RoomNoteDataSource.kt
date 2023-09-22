package com.erif.camvvmdi.framework

import android.content.Context
import com.erif.camvvmdi.framework.db.DatabaseService
import com.erif.camvvmdi.framework.db.NoteEntity
import com.erif.core.data.Note
import com.erif.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(NoteEntity.fromNote(note))
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.getNoteEntity(id)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao.getAllNoteEntity().map { it.toNote() }
    }

    override suspend fun remove(note: Note) {
        noteDao.deleteEntity(NoteEntity.fromNote(note))
    }
}