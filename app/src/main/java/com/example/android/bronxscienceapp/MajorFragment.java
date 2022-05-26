package com.example.android.bronxscienceapp;

//serves same function as list_fragment in swap_app
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MajorFragment extends Fragment{
    private Spinner spin;
    private CourseHelper mDatabase;
    private Button mBack;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_major, container,false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mBack=view.findViewById(R.id.back_button);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        mDatabase = new CourseHelper(getActivity());
        ArrayList<String> majorChoices = mDatabase.getMajorChoices();
        String message="--Select a major--";
        majorChoices.add(0,message);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, majorChoices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin = (Spinner) view.findViewById(R.id.major_selection);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String major =spin.getSelectedItem().toString();
                ArrayList<Course> majorOfferings = mDatabase.getMajorList(major);
                ListAdapter mAdapter = new ListAdapter(majorOfferings, getActivity(),false);
                recyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return view;
    }
}


