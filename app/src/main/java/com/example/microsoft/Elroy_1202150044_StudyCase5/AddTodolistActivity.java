package com.example.microsoft.Elroy_1202150044_StudyCase5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Elroy Yehezkiel
 */

public class AddTodolistActivity extends AppCompatActivity {

    //Deklarasi Variabel
    private EditText name, desc,priority;
    private Button btn_tambah;
    //DataBase
    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add );

        //mengambil variable yang sudah dideklarasikan sesuai id
        name = (EditText) findViewById(R.id.todo_name);
        desc = (EditText) findViewById(R.id.desc);
        priority = (EditText) findViewById(R.id.priority);
        btn_tambah = (Button) findViewById(R.id.tambah);

        //ketika button diklik
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    //Method save yang digunakan pada button tambah todolist untuk menyimpan textnya
    private void save() {
        if (String.valueOf(name.getText()).equals(null) || String.valueOf(desc.getText()).equals("") ||
                String.valueOf(priority.getText()).equals(null) || String.valueOf(priority.getText()).equals("")) {
            //Massage ketika edit text tidak belum diisi dalam 3 detik
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
           db.insert(name.getText().toString().trim(), desc.getText().toString().trim(),priority.getText().toString().trim());
            blank();
            //intent untuk melanjutkan ke activity MainActivity
            Intent i = new Intent(AddTodolistActivity.this,MainActivity.class);
            //Memulai aktivitas yang dideklarasikan sebelumnya
            startActivity(i);
        }
    }

    private void blank() {
        name.requestFocus();
        //Mengatur edit text menjadi kosong dalam 3 detik
        name.setText(null);
        desc.setText(null);
        priority.setText(null);
    }
}
