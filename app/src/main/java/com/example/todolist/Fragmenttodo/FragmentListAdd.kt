package com.example.todolist.Fragmenttodo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.Todo
import java.util.*
const val CRIME_DATE_KEY="AB"
class FragmentListAdd:Fragment(),DatePickerDialogFrahment.DatePickerCallback {
    private lateinit var todoA: Todo
    private lateinit var editTitle: EditText
    private lateinit var editDesccrp: EditText
    private lateinit var dateStr: Button
    private lateinit var addbtm: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_add, container, false)
        editTitle = view.findViewById(R.id.title_to_do)
        editDesccrp = view.findViewById(R.id.desce_to_do)
        dateStr = view.findViewById(R.id.date_start_bton)
        addbtm = view.findViewById(R.id.add_btm)



        return view
    }

    val textWatcherOne = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            todoA.title = s.toString()
        }

        override fun afterTextChanged(s: Editable?) {}
    }
    val textWatcherTwo = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            todoA.description = s.toString()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun onStart() {
        super.onStart()

        dateStr.setOnClickListener {
           todoA=Todo()
            val args = Bundle()
            args.putSerializable(CRIME_DATE_KEY, todoA.dateStart)
            val datePicker = DatePickerDialogFrahment()

            datePicker.arguments = args
            datePicker.setTargetFragment(this, 0)
            datePicker.show(this.parentFragmentManager, "data piker")

        }


    }

    override fun onDateSelected(date: Date) {
        todoA.dateStart=date
        dateStr.text=date.toString()
    }



    }




