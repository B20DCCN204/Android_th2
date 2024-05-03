package com.example.songdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songdemo.R;
import com.example.songdemo.adapter.RecycleViewAdapter;
import com.example.songdemo.dao.SQLiteHelper;
import com.example.songdemo.model.Song;

import java.util.List;

public class FragmentSearch extends Fragment {
    private SQLiteHelper db;
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private Spinner spAlbum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter=new RecycleViewAdapter();
        db=new SQLiteHelper(getContext());
        List<Song> list=db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        spAlbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String album=spAlbum.getItemAtPosition(position).toString();
                List<Song>list;
                if(!album.equalsIgnoreCase("all")){
                    list=db.getByAlbum(album);
                }
                else{
                    list=db.getAll();
                }
                adapter.setList(list);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void initView(View view) {
        recyclerView= view.findViewById(R.id.recyclerView);
        spAlbum = view.findViewById(R.id.spAlbum);

        String []arr=getResources().getStringArray(R.array.album);
        String []arr1=new String [arr.length+1];
        arr1[0]="All";
        for(int i=0;i<arr.length;i++){
            arr1[i+1]=arr[i];
        }
        spAlbum.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));

    }
}
