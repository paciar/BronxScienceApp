package com.example.android.bronxscienceapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<Course> CourseItems;
    private boolean allCourses;

    public ListAdapter(ArrayList<Course> listItems, Context context, boolean all) {
        this.context=context;
        this.CourseItems=listItems;
        this.allCourses=all;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item,parent,false);
        ListViewHolder holder=new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Course i=CourseItems.get(position);
        holder.mCourseName.setText(i.getName());

        //if student have taken course, color code
        /*
        if(i.isTrade()==1){
            ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
            params.height=0;
            holder.itemView.setLayoutParams(params);
        }
         */
    }

    @Override
    public int getItemCount() {
        return CourseItems.size();
    }


    //viewholder
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mCourseName;

        public ListViewHolder(@NonNull View itemView){
            super(itemView);
            mCourseName=itemView.findViewById(R.id.courseName);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if(allCourses) {
                Bundle bundle= new Bundle();
                bundle.putInt("position",getAdapterPosition());
                bundle.putString("id",CourseItems.get(getAdapterPosition()).getId());
                AppCompatActivity activity = (AppCompatActivity) context;
                CourseDetailFragment frag = new CourseDetailFragment();
                frag.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.clistscroll, frag).addToBackStack(null).commit();
            }
        }
    }
}


