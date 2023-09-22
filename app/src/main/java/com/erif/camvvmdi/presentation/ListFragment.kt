package com.erif.camvvmdi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.camvvmdi.R
import com.erif.camvvmdi.framework.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), ListAction {

    private lateinit var recyclerView: RecyclerView
    private val noteListAdapter = NoteListAdapter(arrayListOf(), this)
    private val viewModel: ListViewModel by viewModels()
    private lateinit var loadingView: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.notesListView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteListAdapter
        }

        val addNote: FloatingActionButton = view.findViewById(R.id.addNote)
        addNote.setOnClickListener {
            goToNoteDetail()
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner) { noteList ->
            loadingView.isVisible = false
            recyclerView.isVisible = true
            noteListAdapter.updateNotes(noteList.sortedByDescending { it.updateTime })
        }
    }

    private fun goToNoteDetail(id: Long = 0) {
        val action = ListFragmentDirections.actionGoToNote().setNoteId(id)
        Navigation.findNavController(recyclerView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetail(id)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

}