package com.example.todolist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment


class IamgeMain:Fragment() {
  private lateinit var iamgeTodo:ImageView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
     val view=inflater.inflate(R.layout.imamain,container,false)
    iamgeTodo=view.findViewById(R.id.imageto_do)
    return view
  }

    override fun onStart() {
        super.onStart()
        iamgeTodo.setOnClickListener {
            val fragment = Todolist()
            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_Container, fragment)
                    .commit()

            }
        }
        }


}