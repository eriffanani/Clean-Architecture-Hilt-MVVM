package com.erif.core.data

data class Note (
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    val id: Long = 0,
    var wordCount: Int = 0
)