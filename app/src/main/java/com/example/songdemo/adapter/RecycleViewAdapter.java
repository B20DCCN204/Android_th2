package com.example.songdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songdemo.R;
import com.example.songdemo.model.Song;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Song> list;
    private ItemListener itemListener;

    public void setList(List<Song> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RecycleViewAdapter() {
        list=new ArrayList<>();
    }
    public RecycleViewAdapter(List<Song> list) {
        this.list = list;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Song getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Song item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());
        holder.singer.setText(item.getSinger());
        holder.album.setText(item.getAlbum());
        holder.isFavor.setChecked(item.getFavor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, singer, category, album;
        private CheckBox isFavor;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            singer = view.findViewById(R.id.tvSinger);
            category = view.findViewById(R.id.tvCategory);
            album = view.findViewById(R.id.tvAlbum);
            isFavor = view.findViewById(R.id.cbFavor);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
