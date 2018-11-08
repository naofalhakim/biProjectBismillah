package com.example.user.projectbidanku.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.projectbidanku.FragmentMenu.FavoritNamaFragment;
import com.example.user.projectbidanku.FragmentMenu.RekomendasiNamaFragment;

/**
 * Created by user on 28/05/2018.
 */

public class MainMenuAdapter extends FragmentStatePagerAdapter {

    private int position;

    public MainMenuAdapter(FragmentManager fm, int position) {
        super(fm);
        this.position = position;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RekomendasiNamaFragment();
            case 1:
                return new FavoritNamaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return position;
    }
}
