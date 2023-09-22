package com.erif.core.usecase

import com.erif.core.data.Note
import com.erif.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}