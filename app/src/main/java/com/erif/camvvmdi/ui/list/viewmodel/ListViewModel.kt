package com.erif.camvvmdi.ui.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erif.camvvmdi.domain.NoteUseCases
import com.erif.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val noteList = noteUseCases.getAllNote()
            noteList.forEach { it.wordCount = noteUseCases.getWordCount.invoke(it) }
            notes.postValue(noteList)
        }
    }

}