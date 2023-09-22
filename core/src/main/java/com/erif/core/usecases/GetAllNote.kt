package com.erif.core.usecases

import com.erif.core.repositories.NoteRepository

class GetAllNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNote()
}