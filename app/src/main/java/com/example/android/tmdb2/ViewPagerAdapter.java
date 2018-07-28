package com.example.android.tmdb2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new HomeFragment();
        }
        else if(position == 1){
            return new MoviesFragment();
        }
        else if(position == 2){
            return new TVFragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }
}
