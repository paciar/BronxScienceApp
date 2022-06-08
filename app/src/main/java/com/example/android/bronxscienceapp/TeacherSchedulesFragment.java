package com.example.android.bronxscienceapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class TeacherSchedulesFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    private Button mAddButton1, mSubjectButton;
    MyRecyclerViewAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherSchedulesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherSchedulesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherSchedulesFragment newInstance(String param1, String param2) {
        TeacherSchedulesFragment fragment = new TeacherSchedulesFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // data to populate the RecyclerView with
        ArrayList<String> SGIDays = new ArrayList<>();
        SGIDays.add("Ms. Chambers - Tuesday 9&10, Wednesday 9");
        SGIDays.add("Ms. Qiu - Wednesday 9, Thursday 9&10");
        SGIDays.add("Ms. Lerohl - Wednesday & Thursday 9&10");
        SGIDays.add("Ms. Brooks - Tuesday 9, Wednesday 9&10");
        // set up the RecyclerView
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyRecyclerViewAdapter(getActivity(), SGIDays);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        mAddButton1 = (Button) view.findViewById(R.id.add_button1);
        mAddButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCourse add = new AddCourse();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.clistscroll, add).addToBackStack(null).commit();
            }
        });

        mSubjectButton = (Button) view.findViewById(R.id.subject_button);
        mSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MajorFragment major = new MajorFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.clistscroll, major).addToBackStack(null).commit();
            }
        });
    }

        @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_schedules, container, false);


        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
