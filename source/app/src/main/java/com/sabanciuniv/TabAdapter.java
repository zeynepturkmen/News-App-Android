package com.sabanciuniv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sabanciuniv.model.NewsCategory;

import java.util.List;

public class TabAdapter extends FragmentStateAdapter{
    private int count;
    private List<NewsCategory> cat;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity, int count, List<NewsCategory> cat) {
        super(fragmentActivity);
        this.count = count;
        this.cat = cat;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle b = new Bundle();
        b.putInt("position", position);
        b.putInt("id", cat.get(position).getId());
        Fragment frag = TabFragment.newInstance();
        frag.setArguments(b);
        return frag;
    }

    @Override
    public int getItemCount() {
        return count;
    }
}