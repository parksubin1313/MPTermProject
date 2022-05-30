package com.example.termproject.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.termproject.myFridge_cool;
import com.example.termproject.myFridge_freeze;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(@NonNull FragmentManager fm){
        super(fm);
    }

    //프래그먼트를 보여주는 처리 담당
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return myFridge_cool.newInstance();
            case 1:
                return myFridge_freeze.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "냉장";
            case 1:
                return "냉동";
            default:
                return null;
        }
    }
}
