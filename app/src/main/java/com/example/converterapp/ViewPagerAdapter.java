package com.example.converterapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    // ── Constructor ───────────────────────────────────────────────────────
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // ── Tell ViewPager2 which Fragment to show for each page ──────────────
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new TemperatureFragment();
        } else {
            return new DistanceFragment();
        }
    }

    // ── Tell ViewPager2 how many pages there are ──────────────────────────
    @Override
    public int getItemCount() {
        return 2;
    }
}