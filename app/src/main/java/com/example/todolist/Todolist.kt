package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Fragmenttodo.FragmentListAdd
import com.example.todolist.database.Todo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.math.log

const val KEY_ID="my-todolist"
class Todolist:Fragment() {
    private lateinit var fab_add:FloatingActionButton
  // private lateinit var dat_done:TextView
    val format="EEE, yyyy MMM dd"

    private lateinit var name_todo_list: RecyclerView
    val toDoListViewModel by lazy {
        ViewModelProvider(this).get(ToDoListViewModel::class.java)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.meau_main,menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_recycler, container, false)
        name_todo_list = view.findViewById(R.id.todo_Recycler_VIew)
        val linearLayoutManager= LinearLayoutManager(context)
       name_todo_list.layoutManager = linearLayoutManager
       // dat_done=view.findViewById(R.id.doneTime)
        fab_add=view.findViewById(R.id.fab_add)
        fab_add.setOnClickListener {
            val args=Bundle()
            val fragment= FragmentListAdd()
            fragment.arguments=args
            activity?.let{
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_Container,fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoListViewModel.LiveDataTodo.observe(
            viewLifecycleOwner, Observer {
                updateUI(it)
            }
        )
    }
    private fun updateUI(todos: List<Todo>) {
        val TodoAdapter = TodoAdapter(todos)
        name_todo_list.adapter=TodoAdapter
    }



    private inner class TodoHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var todoA: Todo
        val titleTextView: TextView = itemView.findViewById(R.id.title_item_todo)
        val descrTextView: TextView = itemView.findViewById(R.id.decpation_item_todo)
        val dateStartButton: TextView= itemView.findViewById(R.id.data_start_item)
      val dale_Image:ImageView=itemView.findViewById(R.id.delete_Iamge)
        val edit_Image:ImageView=itemView.findViewById(R.id.edit_ima)
      val   dat_done:TextView=itemView.findViewById(R.id.doneTime)
        val checkBoxIsCheck: CheckBox = itemView.findViewById(R.id.ischeck_item)

        init {
            titleTextView.setOnClickListener(this)
            descrTextView.setOnClickListener(this)
            checkBoxIsCheck.setOnClickListener(this)
            //dateStartButton.setOnClickListener(this)
              //dat_done.setOnClickListener (this)
            // checkBoxIsCheck.setOnClickListener(this)
//            edit_Image.setOnClickListener {
//                toDoListViewModel.updateTodo(todoA)
//            }
            edit_Image.setOnClickListener(this)
            dale_Image.setOnClickListener {
                toDoListViewModel.deleteTodo(todoA)

            }
        }


        fun bing(todo: Todo) {
            this.todoA = todo
            if (todoA.dateStart != null) {
                dateStartButton.text = android.text.format.DateFormat.format(format, todo.dateStart)
            }
            titleTextView.text = todo.title
            descrTextView.text = todo.description

            checkBoxIsCheck.isChecked = todoA.isChecked
            val currentDate=Date()

         if (todoA.dateStart != null){
              if (currentDate.after(todoA.dateStart)) {
                  dat_done.error = ""
              }
          }
//            checkBoxIsCheck.visibility=if (todoA.isChecked){
//                dat_done.visibility=View.GONE
//                View.VISIBLE
//            }else{
//                View.GONE
//            }

//            if (checkBoxIsCheck.isChecked) {
//                todoA.isChecked
//            }
//
//            if (todoA.isChecked) {
//
//            }

            if (todoA.isChecked){
                dale_Image.visibility = View.GONE
                edit_Image.visibility = View.GONE
            }else{
                dale_Image.visibility=View.VISIBLE
                edit_Image.visibility =View.VISIBLE
            }
            checkBoxIsCheck.setOnCheckedChangeListener { buttonView, isChecked ->
                todoA.isChecked = isChecked
                if (todoA.isChecked){
                    dale_Image.visibility = View.GONE
                    edit_Image.visibility = View.GONE
                }else{
                    dale_Image.visibility=View.VISIBLE
                    edit_Image.visibility =View.VISIBLE
                }


                toDoListViewModel.updateTodo(todoA)

            }


            //   checkBoxIsCheck.text=todo.isSoled

        }

        override fun onClick(v: View?) {

            // toDoListViewModel.deleteTodo(todoA)
            if (v == edit_Image) {
                // toDoListViewModel.deleteTodo(todoA)
                val fragment = FragmentListAdd()
                toDoListViewModel.updateTodo(todoA)
                val args = Bundle()
                args.putSerializable(KEY_ID, todoA.id)
                fragment.arguments = args

                activity?.let {
                    it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_Container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }

            }
        }

        private inner class TodoAdapter(var todoRA: List<Todo>) :
            RecyclerView.Adapter<TodoHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
                val view = layoutInflater.inflate(R.layout.list_item_todo, parent, false)
                return TodoHolder(view)
            }

            override fun onBindViewHolder(holder: TodoHolder, position: Int) {
                val todoS = todoRA[position]
                holder.bing(todoS)
            }

            override fun getItemCount(): Int = todoRA.size
        }
    }
