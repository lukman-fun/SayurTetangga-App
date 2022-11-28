package com.sayur.tetangga.cart;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CartAdapter extends FragmentPagerAdapter{
    Context c;
    int tabCount;

    public CartAdapter(@NonNull FragmentManager fm, Context c, int tabCount) {
        super(fm);
        this.c = c;
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new KeranjangFragment();
            case 1:
                return new PesananFrament();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
