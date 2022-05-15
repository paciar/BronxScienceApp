package com.example.android.bronxscienceapp;

//serves same function as list_fragment in swap_app
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

//TODO: Not done
//show a slider inititally
//upon selection, display recyclerview (consider the profile activity page in swapapp)

public class MajorFragment extends Fragment{
    private Spinner spin;
    private CourseHelper mDatabase;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_major, container,false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mDatabase = new CourseHelper(getActivity());
        ArrayList<String> majorChoices = mDatabase.getMajorChoices();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, majorChoices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin = (Spinner) view.findViewById(R.id.major_selection);
        spin.setAdapter(adapter);

        String major = spin.getSelectedItem().toString();
        ArrayList<Course> majorOfferings = mDatabase.getMajorList(major);

        //check for undecided? (if undecided show all courses, or none), consider filter in list_fragment
        ListAdapter mAdapter = new ListAdapter(majorOfferings,getActivity());
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}


