package com.example.microsoft.Elroy_1202150044_StudyCase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Deklarasi variable yang akan digunaka
    private RecyclerView rv;
    private SQLiteDatabase db;
    private DataBase dataHelper;
    private List<Todolist> todoList;
    TodolistAdapter userAdapter;
    protected RecyclerView.LayoutManager LayoutManager;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESC = "desc";
    public static final String PRIORITY = "priority";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menangkap variable yang sudah dideklarasikan berdasarkan id
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //Membuat database baru
        dataHelper =  new DataBase(getApplicationContext());
        //Membaca data dalam database
        db = dataHelper.getReadableDatabase();
        //Membuat araylist baru
        todoList  = new ArrayList<> ();

        //Melakukan inisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //Membuat adapter baru
        userAdapter = new TodolistAdapter(this, todoList, color);
        //Mendefinisikan Linear Layout yang digunkan pada Recycler View
        LayoutManager = new LinearLayoutManager (this);
        //melakukan set et Linear Layout
        rv.setLayoutManager(LayoutManager);
        //melakukan set ukuran tetap untuk recycler view sehingga ketika dilakukan perubahan tidak berubah sizenya
        rv.setHasFixedSize(true);
        //Melakukan inisiasi adapter untuk recycler view
       rv.setAdapter(userAdapter);

        //Menjalankan method data user
        dataUser();
        //Menjalankan method hapus data pada recycler view
        setSwipeForRecyclerView();

        //Ketika button FloatingActionButton di klik
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ketika fab diklik maka akan menjalankan intent ke AddTodolistActivity
                Intent i = new Intent(MainActivity.this,AddTodolistActivity.class);
                //memulai aktivitas berdasarkan intent a yang sudah dideklarasikan
                startActivity(i);
            }
        });
    }

    //Method hapus data pada recycler view dengan swipe
    private void setSwipeForRecyclerView() {
        ItemTouchHelper touchHelper = new ItemTouchHelper ( new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            //Method onMove pada recycler view
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            //Method onSwiped pada recycler view
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                TodolistAdapter adapter = (TodolistAdapter) rv.getAdapter();

                dataHelper.delete(Integer.parseInt(todoList.get(position).getId()));

                todoList.clear();
                dataUser();
                userAdapter.notifyDataSetChanged();
            }
        });
        touchHelper.attachToRecyclerView(rv);
    }

    //Method data user
    private void dataUser() {
        ArrayList<HashMap<String, String>> row = dataHelper.getAllData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(ID);
            String name = row.get(i).get(NAME);
            String desc = row.get(i).get(DESC);
            String priority = row.get(i).get(PRIORITY);

            Todolist data = new Todolist();

            //Set data berdasarkan data yang di get
            data.setId(id);
            data.setName(name);
            data.setDesc(desc);
            data.setPriority(priority);

            todoList.add(data);
        }
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            //ketika diklik akan melakukan intent ke SettingActivity
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            //memuali aktivitas berdasarkan intent yang sudah dideklarasikan
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

