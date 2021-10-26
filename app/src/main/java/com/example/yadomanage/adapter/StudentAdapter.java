package com.example.yadomanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yadomanage.R;
import com.example.yadomanage.vinterface.IClickItemStudent;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private Context mContex;
    private List<Student> mListStudent;
    IClickItemStudent iClickItemStudent;

    public StudentAdapter(List<Student> mListStudent, IClickItemStudent l) {
        this.mListStudent = mListStudent;
        this.iClickItemStudent = l;
    }

    public StudentAdapter(Context mContex) {
        this.mContex = mContex;
    }

    public void setData(List<Student> list){
        this.mListStudent = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        return  new StudentViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student =mListStudent.get(position);
        if(student==null){
            return;
        }
        //holder.imgStudent.setImageResource(student.getS_images());
        holder.nameStudent.setText(student.getS_name());
        holder.isStudent.setText(student.getSid());
        Glide.with(holder.imgStudent.getContext()).load("http://192.168.37.214/yadomanage/image/"+mListStudent.get(position).getS_images()).into(holder.imgStudent);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemStudent.onClickItemStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListStudent != null){
            return mListStudent.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        ImageView imgStudent;
        TextView nameStudent, isStudent;
        CardView layoutItem;



        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStudent = itemView.findViewById(R.id.studentimg);
            nameStudent= itemView.findViewById(R.id.studentname);
            isStudent = itemView.findViewById(R.id.studentid);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }

}












