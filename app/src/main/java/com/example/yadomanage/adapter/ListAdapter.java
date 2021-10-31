package com.example.yadomanage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.model.ClassModel;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.myiewholder> {

    List<Class> data;

    public ListAdapter(List<Class> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myiewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_class_row,parent,false);
        return new myiewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myiewholder holder, int position) {
        holder.txtCid.setText(data.get(position).getCid());
        holder.txtCname.setText(data.get(position).getCname());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myiewholder extends RecyclerView.ViewHolder{
        TextView txtCid,txtCname;
        ImageView imgClass,imgEdit;

        public myiewholder(@NonNull View itemView) {
            super(itemView);
            txtCid = itemView.findViewById(R.id.classid);
            txtCname = itemView.findViewById(R.id.classname);
            imgClass = itemView.findViewById(R.id.icclass);
            //imgEdit = itemView.findViewById(R.id.edit);
        }
    }

}
