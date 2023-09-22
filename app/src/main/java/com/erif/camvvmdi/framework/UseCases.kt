package com.erif.camvvmdi.framework

import com.erif.core.usecase.AddNote
import com.erif.core.usecase.GetAllNote
import com.erif.core.usecase.GetNote
import com.erif.core.usecase.GetWordCount
import com.erif.core.usecase.RemoveNote

data class UseCases (
    val addNote: AddNote,
    val getAllNote: GetAllNote,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)