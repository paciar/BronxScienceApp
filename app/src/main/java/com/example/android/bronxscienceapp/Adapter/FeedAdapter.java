package com.example.android.bronxscienceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bronxscienceapp.Interface.ItemClickListener;
import com.example.android.bronxscienceapp.Model.Feed;
import com.example.android.bronxscienceapp.Model.Item;
import com.example.android.bronxscienceapp.Model.RSSObject;
import com.example.android.bronxscienceapp.R;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView txtTitle, txtPubDate, txtContent;
    private ItemClickListener mItemClickListener;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtPubDate = (TextView) itemView.findViewById(R.id.txtPubDate);
        txtContent = (TextView) itemView.findViewById(R.id.txtContent);

        // Set event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        mItemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{

    private RSSObject mRSSObject;
    private Context mContext;
    private LayoutInflater mInflater;

    public FeedAdapter(RSSObject RSSObject, Context context) {
        mRSSObject = RSSObject;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.row, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.txtTitle.setText(mRSSObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(mRSSObject.getItems().get(position).getPubDate());
        holder.txtContent.setText(mRSSObject.getItems().get(position).getContent());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRSSObject.getItems().get(position).getLink()));
                    mContext.startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRSSObject.items.size();
    }
}
