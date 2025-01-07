package com.wjjang.stopwatch.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.wjjang.stopwatch.R
import com.wjjang.stopwatch.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        noteEditText = binding.noteEditText

        val savedNote = loadNote(requireContext())
        noteEditText.setText(savedNote ?: "")

        noteEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                val note = charSequence.toString()
                saveNote(requireContext(), note)
            }

            override fun afterTextChanged(editable: Editable?) {
            }
        })

        return binding.root
    }

    private fun saveNote(context: Context, note: String) {
        val sharedPreferences = context.getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("note_key", note)
        editor.apply()
    }

    private fun loadNote(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("note_key", "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
