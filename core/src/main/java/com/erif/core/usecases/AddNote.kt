package com.erif.core.usecases

import com.erif.core.data.Note
import com.erif.core.repositories.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}