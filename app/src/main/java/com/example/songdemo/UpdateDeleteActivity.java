package com.example.songdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.songdemo.dao.SQLiteHelper;
import com.example.songdemo.model.Song;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eTitle, eSinger;
    private Spinner spCategory, spAlbum;
    private CheckBox cbIsFavor;
    private Button btUpdate, btRemove, btBack;
    private Song item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent=getIntent();
        item = (Song) intent.getSerializableExtra("item");
        eTitle.setText(item.getTitle());
        eSinger.setText(item.getSinger());

        ArrayAdapter<String> albumAdapter = (ArrayAdapter<String>) spAlbum.getAdapter();
        int albumPosition = albumAdapter.getPosition(item.getAlbum());
        spAlbum.setSelection(albumPosition);

        ArrayAdapter<String> categoryAdapter = (ArrayAdapter<String>) spCategory.getAdapter();
        int categoryPosition = categoryAdapter.getPosition(item.getCategory());
        spCategory.setSelection(categoryPosition);

        cbIsFavor.setChecked(item.getFavor());
    }

    private void initView() {
        eTitle = findViewById(R.id.editTextTitle);
        eSinger = findViewById(R.id.editTextSinger);
        spAlbum = findViewById(R.id.spinnerAlbum);
        spCategory = findViewById(R.id.spinnerCategory);
        cbIsFavor = findViewById(R.id.checkBoxIsFavor);
        btUpdate = findViewById(R.id.buttonUpdate);
        btBack = findViewById(R.id.buttonBack);
        btRemove = findViewById(R.id.buttonRemove);
        spCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
        spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.album)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db= new SQLiteHelper(this);
        if(view==btBack){
            finish();
        }
        if(view==btUpdate){
            String t = eTitle.getText().toString();
            String s = eSinger.getText().toString();
            String a = spAlbum.getSelectedItem().toString();
            String c = spCategory.getSelectedItem().toString();
            boolean fa = cbIsFavor.isChecked();
            if (!t.isEmpty() && !s.isEmpty()) {
                Song i = new Song(item.getId(), t, s, a, c, fa);
                db.updateSong(i);
                finish();
            }
        }
        if(view== btRemove){
            int id=item.getId();
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setTitle("Ban co chac muon xoa "+item.getTitle()+" khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.deleteSong(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }
}