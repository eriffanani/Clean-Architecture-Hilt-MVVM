package com.erif.core.usecase

import com.erif.core.data.Note

class GetWordCount {

    operator fun invoke(note: Note) = getCount(note.content)

    private fun getCount(string: String): Int {
        return string.split(" ", "\n").count {
            it.contains(Regex(".*[a-zA-Z].*"))
        }
    }

}