package com.example.roomdatabasepractice

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasepractice.databinding.ActivityMainBinding
import com.example.roomdatabasepractice.entity.Task
import com.example.roomdatabasepractice.viewmodel.TaskViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: TaskViewModel

    private lateinit var editText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        editText = binding.editText

        binding.insertButton.setOnClickListener {
            val inputText = editText.text.toString()
            val task = Task(
                title = inputText,
                description = "Description for $inputText"
            )
            viewModel.insert(task)
            editText.text?.clear()
        }


        fetchData()


    }
    private fun fetchData(){
        viewModel.allTasks.observe(this) { tasks ->
            // Update UI with the list of tasks
            Log.d(TAG, "Fetched ${tasks.size} tasks: $tasks")
        }

    }
}