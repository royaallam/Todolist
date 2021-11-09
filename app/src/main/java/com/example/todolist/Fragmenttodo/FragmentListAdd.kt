package com.example.todolist.Fragmenttodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class FragmentListAdd:Fragment() {
    private lateinit var editTitle: EditText
    private lateinit var editDesccrp: EditText
    private lateinit var dateStr: Button
    private lateinit var dateEnd: Button
   private lateinit var addbtm:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_add, container, false)
        editTitle = view.findViewById(R.id.title_to_do)
        editDesccrp = view.findViewById(R.id.desce_to_do)
        dateStr = view.findViewById(R.id.date_start_bton)
        dateEnd = view.findViewById(R.id.date_end_btm)
       addbtm=view.findViewById(R.id.add_btm)
        return view
    }

}




