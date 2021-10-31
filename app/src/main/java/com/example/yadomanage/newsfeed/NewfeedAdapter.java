package com.example.yadomanage.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadomanage.R;
import com.example.yadomanage.chat.Message;

import java.util.ArrayList;

public class NewfeedAdapter extends RecyclerView.Adapter<NewfeedAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Newsfeed> newsfeeds;

    public NewfeedAdapter(Context context, ArrayList<Newsfeed> newsfeeds) {
        this.context = context;
        this.newsfeeds = newsfeeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsfeed_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Newsfeed newsfeed  = newsfeeds.get(position);
        holder.textViewMessage.setText(newsfeed.getNf_content());
        holder.textViewTime.setText(newsfeed.getNf_time());
    }

    @Override
    public int getItemCount() {
        return newsfeeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessage;
        public TextView textViewTime;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewMessage = (TextView) itemView.findViewById(R.id.textViewMessage);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
        }
    }
}
