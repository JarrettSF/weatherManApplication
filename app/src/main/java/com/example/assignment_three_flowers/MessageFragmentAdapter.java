package com.example.assignment_three_flowers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MessageFragmentAdapter extends FragmentStateAdapter {

    public MessageFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {

        super(fragmentManager, lifecycle);
    }

    public Fragment createFragment(int position) {

        if (position == 0)

            return new location_view();

        else if (position == 1)

            return new location_editor();

       else if (position == 2)

            return new planTrip();

        return null;
    }

    public int getItemCount() {return 3;}


    }











