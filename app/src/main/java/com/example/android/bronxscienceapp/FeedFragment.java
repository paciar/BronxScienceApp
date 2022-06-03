package com.example.android.bronxscienceapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android.bronxscienceapp.Feeds.AthleticNewsFragment;
import com.example.android.bronxscienceapp.Feeds.CalendarFragment;
import com.example.android.bronxscienceapp.Feeds.DailyAnnouncementsFragment;
import com.example.android.bronxscienceapp.Feeds.NewsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    Spinner mFeed;
    Fragment mSelectedFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mSelectedFragment = CalendarFragment.newInstance("","");

        // Make drop down menu visible & set a listener
        mFeed = (Spinner) view.findViewById(R.id.feed);
        List<String> feedItems = Arrays.asList("Calendar", "News", "Daily Announcements", "Athletic News");
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, feedItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFeed.setAdapter(spinnerAdapter);

        mFeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int spinnerPosition = mFeed.getSelectedItemPosition();
                Log.i("spinnerPosition", Integer.toString(spinnerPosition));
                switch (spinnerPosition) {
                    case 0:
                        mSelectedFragment = CalendarFragment.newInstance("", "");
                        break;
                    case 1:
                        mSelectedFragment = NewsFragment.newInstance("", "");
                        break;
                    case 2:
                        mSelectedFragment = DailyAnnouncementsFragment.newInstance("","");
                    case 3:
                        mSelectedFragment = AthleticNewsFragment.newInstance("","");
                }
                getFragmentManager().beginTransaction().replace(R.id.spinner_layout, mSelectedFragment, null).addToBackStack(null).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}});

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.spinner_layout, mSelectedFragment);
        transaction.commit();

        return view;
    }
}