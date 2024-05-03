package com.example.songdemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.songdemo.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "th02.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE songs("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT, singer TEXT, category TEXT, album TEXT, isfavor INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    // getAll
    public List<Song> getAll(){
        List<Song> list= new ArrayList<>();
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs=st.query("songs",null,null,null,null,null,null);
        while(rs !=null && rs.moveToNext()){
            int id= rs.getInt(0);
            String title= rs.getString(1);
            String singer= rs.getString(2);
            String category= rs.getString(3);
            String album= rs.getString(4);
            int isfavor = rs.getInt(5);
            list.add(new Song(id,title,singer,album,category,isfavor == 1 ? true : false));
        }
        return list;
    }

    //add item
    public long addSong(Song i){
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("singer", i.getSinger());
        values.put("album", i.getAlbum());
        values.put("category", i.getCategory());
        values.put("isfavor", i.getFavor() ? 1 : 0);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("songs",null, values);
    }
    //update
    public int updateSong(Song i) {
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("singer", i.getSinger());
        values.put("album", i.getAlbum());
        values.put("category", i.getCategory());
        values.put("isfavor", i.getFavor() ? 1 : 0);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("songs",
                values, whereClause, whereArgs);
    }
    //delete
    public int deleteSong(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("songs",
                whereClause, whereArgs);
    }
    //search
    public List<Song> getByAlbum(String album){
        List<Song> list=new ArrayList<>();
        String whereClase="album like ?";
        String[] whereArgs={album};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("songs", null, whereClase, whereArgs, null, null, null);
        while(rs!=null && rs.moveToNext()){
            int id= rs.getInt(0);
            String title= rs.getString(1);
            String singer= rs.getString(2);
            String category= rs.getString(3);
            String al = rs.getString(4);
            int isfavor = rs.getInt(5);
            list.add(new Song(id,title,singer,al,category,isfavor == 1 ? true : false));
        }
        return list;
    }

}
