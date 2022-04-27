package com.example.android.bronxscienceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    static BottomNavigationView sBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sBottomNavigationView =findViewById(R.id.bottom_navigation);

        // When HomeActivity is started, it should open HomeFragment by default.

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, HomeFragment.newInstance("", ""));
        fragmentTransaction.commit();

        View view = sBottomNavigationView.findViewById(R.id.navigation_home);
        view.performClick();

        sBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = HomeFragment.newInstance("","");
                        break;
                    case R.id.navigation_courses:
                        selectedFragment = CoursesFragment.newInstance("","");
                        break;
                    case R.id.navigation_teacherSchedules:
                        selectedFragment = TeacherSchedulesFragment.newInstance("","");
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, selectedFragment);
                transaction.commitAllowingStateLoss();

                return true;
            }
        });
    }
}