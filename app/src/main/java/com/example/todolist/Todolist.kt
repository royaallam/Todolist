package com.example.todolist

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Fragmenttodo.FragmentListAdd
import com.example.todolist.Fragmenttodo.Tododate
import com.example.todolist.database.Todo
import com.google.android.material.floatingactionbutton.FloatingActionButton
const val KEY_ID="my-todolist"
class Todolist:Fragment() {
    private lateinit var fab_add:FloatingActionButton
    private lateinit var name_todo_list: RecyclerView
    val toDoViewModel by lazy {
        ViewModelProvider(this).get(toDoViewModel::class.java)
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

        fab_add=view.findViewById(R.id.fab_add)
        fab_add.setOnClickListener {
        }
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoViewModel.LiveDataTodo.observe(
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
        val dateEndButton: TextView = itemView.findViewById(R.id.data_end_item)
        val checkBoxIsCheck: CheckBox = itemView.findViewById(R.id.ischeck_item)

        init {
            titleTextView.setOnClickListener(this)
            descrTextView.setOnClickListener(this)
            dateStartButton.setOnClickListener(this)
            dateEndButton.setOnClickListener(this)
            // checkBoxIsCheck.setOnClickListener(this)
        }


        fun bing(todo: Todo) {
            this.todoA = todo
            titleTextView.text = todo.title
            descrTextView.text = todo.description
            dateStartButton.text = todo.dateStart.toString()
            dateEndButton.text = todo.dateEnd.toString()
            //   checkBoxIsCheck.text=todo.isSoled

        }

        override fun onClick(v: View?) {
            if (v==itemView){
                val args=Bundle()
                args.putSerializable(KEY_ID,todoA.id)
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
