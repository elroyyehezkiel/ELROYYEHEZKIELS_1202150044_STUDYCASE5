package com.example.microsoft.Elroy_1202150044_StudyCase5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Elroy Yehezkiel
 */

public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.TodoViewHolder> {

    //Deklarasi variable yang akan digunakan
    private List<Todolist> todoList;
    private Context context;
    int id;

    //Konstruktor dari class TodolistAdapter
    public TodolistAdapter(Context cont, List<Todolist> listTodo, int id){
        this.todoList = listTodo;
        this.context = cont;
        this.id = id;
    }

    @Override
    public TodolistAdapter.TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_todo_list, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodolistAdapter.TodoViewHolder holder, int position) {
        // Mendapatkan elemen dari dataset kemudian engganti isi tampilan dengan elemen itu
        Todolist todo = todoList.get(position);
        holder.name.setText(todo.getName());
        holder.desc.setText(todo.getDesc());
        holder.priority.setText(todo.getPriority());
        holder.cd.setCardBackgroundColor(context.getResources().getColor(this.id));

        //Memberikan respon ketika salah satu item view diklik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ketika diklik akan melakukan intent ke MainActivity
                Intent i = new Intent(view.getContext().getApplicationContext(), MainActivity.class);
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return todoList.size();
    }



    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc,priority;
        public CardView cd;

        public TodoViewHolder(View itemView) {
            super(itemView);
            //Menangkap variable yang sudah dideklarasikan berdasarkan id
            name  = itemView.findViewById(R.id.name);
            desc   = itemView.findViewById(R.id.desc);
            priority   = itemView.findViewById(R.id.priority);
            cd = itemView.findViewById(R.id.card_view);
        }
    }
}
