package com.example.assignment_three_flowers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class tripPlanned extends AppCompatActivity {

    MessageViewModel messageViewModel;
    private TabLayout tabLayout;

    private ViewPager2 viewPager2;

    private MessageFragmentAdapter messageFragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_plan_trip);

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);


        messageViewModel.setTripDetails(new TripDetails());

        tabLayout = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.viewer);

        FragmentManager fragmentManager = getSupportFragmentManager();
        messageFragmentAdapter = new MessageFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(messageFragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Trip"));
        tabLayout.addTab(tabLayout.newTab().setText("Plan Trip"));
        tabLayout.addTab(tabLayout.newTab().setText("Add People"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        Button close = findViewById(R.id.close);
        close.setOnClickListener((View view) -> finish());



    } }
