package com.erif.core.usecase

import com.erif.core.data.Note
import com.erif.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}