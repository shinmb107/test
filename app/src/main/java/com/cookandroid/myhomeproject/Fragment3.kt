package com.cookandroid.myhomeproject

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.cookandroid.myhomeproject.databinding.FragMent3Binding
import java.util.*

class Fragment3 : Fragment() {
    lateinit var binding3: FragMent3Binding
    var listTodo: List<Todo> = ArrayList()
    lateinit var db: SQLiteDatabase
    lateinit var adapter: ListTodoAdapter
    var title: String = ""
    var message: String = ""
    //var writedate: Long = 0
    //lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_ment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding3 = FragMent3Binding.bind(view)
        val myDb = MyDBHelper(requireActivity())
        db = myDb.writableDatabase
        listTodo = allTodo()
        adapter = ListTodoAdapter(requireContext(), listTodo)
        binding3.listView.adapter = adapter

        binding3.fabAdd.setOnClickListener {
            val dview = View.inflate(requireContext(), R.layout.dig_cal, null)
            val dlgView = AlertDialog.Builder(requireContext())

            dlgView.setTitle("일정추가")
            dlgView.setView(dview)
            dlgView.setPositiveButton("추가") { dialog, which ->
                title = dview.findViewById<EditText>(R.id.edtPerson).text.toString()
                message = dview.findViewById<EditText>(R.id.edtMemo).text.toString()

                val calendarView = dview.findViewById<CalendarView>(R.id.calDate)
                val selectedDate = calendarView.date

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selectedDate
                val writedate = calendar.timeInMillis

                val todo = Todo(title, message, writedate)
                addTodo(todo)

                listTodo = allTodo()
                adapter = ListTodoAdapter(requireContext(), listTodo)
                binding3.listView.adapter = adapter
            }

            dlgView.setNegativeButton("취소", null)
            dlgView.show()
        }

        binding3.fabDel.setOnClickListener {
            deleteTodo()
            listTodo = allTodo()
            adapter = ListTodoAdapter(requireContext(), listTodo)
            binding3.listView.adapter = adapter
        }
    }

    fun allTodo(): List<Todo> {
        val listTodo = ArrayList<Todo>()
        val sql = "SELECT * FROM ${MyDBHelper.TABLE_NAME}"
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val todo = Todo()
                todo.title = cursor.getString(1)
                todo.message = cursor.getString(2)
                todo.writedate = cursor.getLong(3)
                listTodo.add(todo)
            } while (cursor.moveToNext())
        }
        return listTodo
    }

    fun addTodo(todo: Todo) {
        val sql = "INSERT INTO ${MyDBHelper.TABLE_NAME}(title, message, writedate) VALUES(?,?,?)"
        val args = arrayOf(todo.title, todo.message, todo.writedate.toString())
        db.execSQL(sql, args)
    }

    fun deleteTodo() {
        val sql = "DELETE FROM ${MyDBHelper.TABLE_NAME}"
        db.execSQL(sql)
    }
}
