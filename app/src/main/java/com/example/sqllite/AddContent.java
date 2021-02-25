package com.example.sqllite;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddContent extends AppCompatActivity implements View.OnClickListener, RecyclerviewAdapter.OnUserClickListener {

    EditText judul, desc;
    Button submit;
    Context context;
    home home;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        judul = findViewById(R.id.judul);
        desc = findViewById(R.id.deskripsi);
        submit = findViewById(R.id.btnSubmit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            DatabaseHelper db = new DatabaseHelper(this);
            Data data = new Data();
            String btn = submit.getText().toString();
            if (btn.equals("Submit")) {
                data.setJudul(judul.getText().toString());
                data.setDeskripsi(desc.getText().toString());
                db.insert(data);
            }
            if (btn.equals("Update")) {
                data.setJudul(judul.getText().toString());
                data.setDeskripsi(desc.getText().toString());
                db.update(data);
            }
            home.setupRecyclerView();
            judul.setText("");
            desc.setText("");
            judul.setFocusable(true);
            submit.setText("Submit");
        }
    }

    public void onUserClick(Data data, String action){
        if (action.equals("Edit")){
            judul.setText(data.getJudul());
            judul.setFocusable(false);
            desc.setText(data.getDeskripsi());
            submit.setText("Update");
        }
        if (action.equals("Delete")){
            DatabaseHelper db = new DatabaseHelper(this);
            db.delete(data.getJudul());
            home.setupRecyclerView();
        }
    }
}
