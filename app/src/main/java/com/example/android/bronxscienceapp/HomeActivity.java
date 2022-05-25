package com.example.android.bronxscienceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    static BottomNavigationView sBottomNavigationView;

    private TextView mName, mMail;
    private Button mLogout;

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sBottomNavigationView =findViewById(R.id.bottom_navigation);

        /*
        mName = findViewById(R.id.name);
        mMail = findViewById(R.id.mail);
        mLogout = findViewById(R.id.logout);
         */


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String name = account.getDisplayName();
            String mail = account.getEmail();
            /*
            mName.setText(name);
            mMail.setText(mail);
             */
        }

        /*
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
         */

        // When HomeActivity is started, it should open CalendarFragment by default.

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, CalendarFragment.newInstance("", ""));
        fragmentTransaction.commit();

        View view = sBottomNavigationView.findViewById(R.id.navigation_home);
        view.performClick();

        sBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = CalendarFragment.newInstance("","");
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

    private void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class ));
            }
        });
    }
}