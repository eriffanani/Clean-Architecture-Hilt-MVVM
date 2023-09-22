package com.erif.camvvmdi.ui.form

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.erif.camvvmdi.R
import com.erif.camvvmdi.ui.form.viewmodel.NoteViewModel
import com.erif.core.data.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment(), MenuProvider {

    private var noteId = 0L
    private val viewModel: NoteViewModel by viewModels()
    private var currentNote = Note("", "", 0, 0)
    private lateinit var mView: View

    private lateinit var titleView: EditText
    private lateinit var contentView: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }

        titleView = view.findViewById(R.id.titleView)
        contentView = view.findViewById(R.id.contentView)

        val check: FloatingActionButton = view.findViewById(R.id.checkButton)
        check.setOnClickListener {
            if (titleView.text.toString() != "" && contentView.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = titleView.text.toString()
                currentNote.content = contentView.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }

            Navigation.findNavController(it).popBackStack()
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(mView).popBackStack()
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                titleView.setText(it.title, TextView.BufferType.EDITABLE)
                contentView.setText(it.content, TextView.BufferType.EDITABLE)
            }
        }

    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mView.windowToken, 0)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.menuDelete) {
            if (noteId != 0L) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Are you sure want to delete this note?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteNote(currentNote)
                    }
                    .setNegativeButton("Cancel") { _, _ -> }
                    .create()
                    .show()
            }
        }
        return false
    }

}