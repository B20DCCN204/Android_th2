package com.example.songdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.songdemo.dao.SQLiteHelper;
import com.example.songdemo.model.Song;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eTitle, eSinger;
    private Spinner spCategory, spAlbum;
    private CheckBox cbIsFavor;
    private Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }
    private void initView() {
        eTitle = findViewById(R.id.editTextTitle);
        eSinger = findViewById(R.id.editTextSinger);
        spAlbum = findViewById(R.id.spinnerAlbum);
        spCategory = findViewById(R.id.spinnerCategory);
        cbIsFavor = findViewById(R.id.checkBoxIsFavor);
        btUpdate = findViewById(R.id.buttonUpdate);
        btCancel = findViewById(R.id.buttonCancel);
        spCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
        spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.album)));
    }

    @Override
    public void onClick(View view) {
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            String t = eTitle.getText().toString();
            String s = eSinger.getText().toString();
            String a = spAlbum.getSelectedItem().toString();
            String c = spCategory.getSelectedItem().toString();
            boolean fa = cbIsFavor.isChecked();
            if (!t.isEmpty() && !s.isEmpty()) {
                Song i = new Song(t, s, a, c, fa);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addSong(i);
                finish();
            }

        }
    }
}