package com.erif.core.usecase

import com.erif.core.data.Note
import com.erif.core.repository.NoteRepository

class GetAllNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNote()
}