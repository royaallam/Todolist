package com.example.todolist.Fragmenttodo

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerDialogFrahment:DialogFragment() {
    val format="yyyy-MM-ddd"


    interface DatePickerCallback{
        fun  onDateSelected(date: Date)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val date = arguments?.getSerializable(TODO_DATE_KEY) as Date

        val calendar = Calendar.getInstance()

        calendar.time = date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datelistener = DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            val resultDate = GregorianCalendar(year, month, dayOfMonth).time
            targetFragment?.let {
                (it as DatePickerCallback).onDateSelected(resultDate)
            }
        }



        return DatePickerDialog(
            requireContext(),
            datelistener,
            year,
            month,
            day
        )
    }
}