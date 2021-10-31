package com.example.yadomanage.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yadomanage.R;

import java.util.ArrayList;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ViewHolder> {

    //user id
    private String userId;
    private Context context;

    //Tag for tracking self message
    private int SELF = 786;

    //ArrayList of messages object containing all the messages in the thread
    private ArrayList<Message> messages;

    //Constructor
    public ThreadAdapter(Context context, ArrayList<Message> messages, String userId){
        this.userId = userId;
        this.messages = messages;
        this.context = context;
    }

    //IN this method we are tracking the self message
    @Override
    public int getItemViewType(int position) {
        //getting message object of current position
        Message message = messages.get(position);

        Log.d("===","-----------------lan lap");
//        Log.d("===",messages.size()+"Dolon");
        Log.d("===",message.getAu()+"---ky hieu");
//        Log.d("===",message.getM_content()+" noi dung");

            if (message.getAu().equals("t")){
                return SELF;}
            else{
                return position;
            }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Creating view
        View itemView;
        //if view type is self
        if (viewType == SELF) {
            //Inflating the layout self
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_thread, parent, false);
        } else {
            //else inflating the layout others
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_thread_other, parent, false);
        }
        //returing the view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Adding messages to the views
        Message message = messages.get(position);
        holder.textViewMessage.setText(message.getM_content());
        holder.textViewTime.setText(message.getM_time());
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    //Initializing views
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
