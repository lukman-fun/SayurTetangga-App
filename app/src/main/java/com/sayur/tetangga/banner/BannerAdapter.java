package com.sayur.tetangga.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.sayur.tetangga.R;
import com.sayur.tetangga.utils.Constaint;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    Context c;
    List<Banner.Data> data;

    public BannerAdapter(Context c, List<Banner.Data> data) {
        this.c = c;
        this.data = data;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_banner, container, false);
        ImageView img = v.findViewById(R.id.imgbanner);
        Glide.with(c).load(Constaint.BASE_URL + data.get(position).getGambar()).into(img);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
