package com.erif.camvvmdi.domain

import com.erif.core.usecases.AddNote
import com.erif.core.usecases.GetAllNote
import com.erif.core.usecases.GetNote
import com.erif.core.usecases.GetWordCount
import com.erif.core.usecases.RemoveNote

data class NoteUseCases (
    val addNote: AddNote,
    val getAllNote: GetAllNote,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)