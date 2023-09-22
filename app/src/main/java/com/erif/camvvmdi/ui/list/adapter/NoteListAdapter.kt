package com.erif.camvvmdi.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erif.camvvmdi.R
import com.erif.camvvmdi.ui.list.ListAction
import com.erif.core.data.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteListAdapter(private val notes: ArrayList<Note>, val action: ListAction): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note, parent, false
            )
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.itemTitle)
        private val content: TextView = itemView.findViewById(R.id.itemContent)
        private val creation: TextView = itemView.findViewById(R.id.itemCreate)
        private val wordCount: TextView = itemView.findViewById(R.id.itemWord)

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content
            val wordSize = "${note.wordCount} Word"
            wordCount.text = wordSize

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss", Locale.getDefault())
            val resultDate = Date(note.updateTime)
            val date = "Last update ${sdf.format(resultDate)}"
            creation.text = date

            itemView.setOnClickListener {
                action.onClick(note.id)
            }

        }
    }

}