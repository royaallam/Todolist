package com.example.todolist.Fragmenttodo

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.todolist.*
import com.example.todolist.database.Todo
import java.util.*
 const val TODO_DATE_KEY="AB"
    class FragmentListAdd:Fragment(),DatePickerDialogFrahment.DatePickerCallback {
    private lateinit var todoA: Todo
    private lateinit var editTitle: EditText
    private lateinit var editDesccrp: EditText
    private lateinit var dateStr: Button
    private lateinit var addbtm: Button
    private lateinit var editup:Button
    val format="yyyy-MM-ddd"

    private val framentViewModel by lazy{
        ViewModelProvider(this).get(TodoAFragmentViewModel::class.java) }
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
        editup=view.findViewById(R.id.edit_btn)

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
        editTitle.addTextChangedListener(textWatcherOne)
        editDesccrp.addTextChangedListener(textWatcherTwo)
//        isSolvedCheckBox.setOnCheckedChangeListener { _, isChecked ->
//            crime.isSolved=isChecked

        dateStr.setOnClickListener {

            val args = Bundle()
            args.putSerializable(TODO_DATE_KEY, todoA.dateStart)
            val datePicker = DatePickerDialogFrahment()

            datePicker.arguments = args
            datePicker.setTargetFragment(this, 0)
            datePicker.show(this.parentFragmentManager, "data piker")

        }
      addbtm.setOnClickListener {

          framentViewModel.addTodo(todoA)

          val fragment = Todolist()

          activity?.let {
              it.supportFragmentManager.beginTransaction()
                  .replace(R.id.fragment_Container, fragment)
                  .commit()
          }

      }
        editup.setOnClickListener {

            framentViewModel.saveUpdata(todoA)
            val fragment = Todolist()

            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_Container, fragment)
                    .commit()
            }

        }




    }

    override fun onDateSelected(date: Date) {
        todoA.dateStart=date
        dateStr.text= DateFormat.format(format,todoA.dateStart)

    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
          todoA= Todo()
            val todoId=arguments?.getSerializable(KEY_ID) as UUID?
                todoId?.let {
                    framentViewModel.loadTodo(it)
                }

        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            framentViewModel.todoLiveData.observe(
                viewLifecycleOwner,androidx.lifecycle.Observer {
                    it?.let {
                        todoA=it
                        editTitle.setText(it.title)
                        editDesccrp.setText(it.description)
                        dateStr.text=it.dateStart.toString()

                    }
                }
            )
        }
        override fun onStop() {

            super.onStop()
            framentViewModel.saveUpdata(todoA)

        }




    }




