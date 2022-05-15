package com.example.android.bronxscienceapp;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CoursesFragment extends Fragment {
    private Button mAddButton;
    private CourseHelper mDatabase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_courses, container,false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mDatabase = new CourseHelper(getActivity());
        ArrayList<Course> allCourses = mDatabase.getCourseList();
        boolean empty=allCourses.isEmpty();
        if (!empty) {
            recyclerView.setVisibility(View.VISIBLE);
            ListAdapter mAdapter = new ListAdapter(allCourses,getActivity());
            recyclerView.setAdapter(mAdapter);
        }
        else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "No courses yet", Toast.LENGTH_LONG).show();
        }

        mAddButton = (Button) view.findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: fix back button
                Intent intent=new Intent(getActivity(),AddCourse.class);
                startActivity(intent);
            }
        });
        return view;
    }
}