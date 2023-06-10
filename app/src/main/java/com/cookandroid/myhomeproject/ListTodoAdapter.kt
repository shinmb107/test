package com.cookandroid.myhomeproject


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cookandroid.myhomeproject.databinding.ItemBinding

class ListTodoAdapter(private val context: Context, private val listTodo: List<Todo>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return listTodo.size
    }

    override fun getItem(position: Int): Any {
        return listTodo[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val todo = listTodo[position]
        binding.txtRowTitle.text = "${todo.title}: ${todo.message}"
        binding.txtRowDate.text = android.text.format.DateFormat.format("yyyy/MM/dd", todo.writedate)
        return binding.root
    }
}