package com.example.songdemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.songdemo.fragment.FragmentHome;
import com.example.songdemo.fragment.FragmentInfo;
import com.example.songdemo.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentInfo();
            case 2: return new FragmentSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
