package com.example.yadomanage.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.sharedPreferences.DataLocalManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkAdapter  extends RecyclerView.Adapter{
    private List<Marks> movieList;
    Mcondition mcondition;

    public MarkAdapter(List<Marks> movieList) {
        this.movieList = movieList;
    }
    public void setData(List<Marks> list){
        this.movieList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_mark_view, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {


            rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtHi.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtRank.setText("MSSV");
            rowViewHolder.txtMovieName.setText("Ho Ten");
            rowViewHolder.txtYear.setText("DTBHK");
            rowViewHolder.txtCost.setText("DTBTL");
            rowViewHolder.txtHi.setText("DRL");
        } else {
            getCondition(rowViewHolder,rowPos);
//            Marks modal = (Marks) movieList.get(rowPos - 1);
////            float i = Float.parseFloat()
//
//            if(Float.parseFloat(modal.getR_semmark())<1 ||
//                    Float.parseFloat(modal.getR_avemark())<2 ||
//            Integer.parseInt(modal.getR_trainmark())<60){
//
//                rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_content_cell_danger);
//                rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_danger);
//                rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_danger);
//                rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_danger);
//                rowViewHolder.txtHi.setBackgroundResource(R.drawable.table_content_cell_danger);
//
//                rowViewHolder.txtRank.setText(modal.getSid() + "");
//                rowViewHolder.txtMovieName.setText(modal.getS_name());
//                rowViewHolder.txtYear.setText(modal.getR_semmark() + "");
//                rowViewHolder.txtCost.setText(modal.getR_avemark() + "");
//                rowViewHolder.txtHi.setText(modal.getR_trainmark()+ "");
//
//            }else{
//
//            rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtHi.setBackgroundResource(R.drawable.table_content_cell_bg);
//
//            rowViewHolder.txtRank.setText(modal.getSid() + "");
//            rowViewHolder.txtMovieName.setText(modal.getS_name());
//            rowViewHolder.txtYear.setText(modal.getR_semmark() + "");
//            rowViewHolder.txtCost.setText(modal.getR_avemark() + "");
//            rowViewHolder.txtHi.setText(modal.getR_trainmark()+ "");
//            }
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size() + 1;
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtRank;
        TextView txtMovieName;
        TextView txtYear;
        TextView txtCost;
        TextView txtHi;

        RowViewHolder(View itemView) {
            super(itemView);
            txtRank = itemView.findViewById(R.id.txtRank);
            txtMovieName = itemView.findViewById(R.id.txtMovieName);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtCost = itemView.findViewById(R.id.txtCost);
            txtHi = itemView.findViewById(R.id.txtHi);
        }
    }

    private void getCondition(RowViewHolder rowViewHolder,int rowPos){
        Call<Mcondition> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showMcondition(Integer.parseInt(DataLocalManager.getStringId()));

        call.enqueue(new Callback<Mcondition>() {
            @Override
            public void onResponse(Call<Mcondition> call, Response<Mcondition> response) {
                mcondition= response.body();
                //Toast.makeText(getContext(), mcondition.getTrainmarks()+"", Toast.LENGTH_LONG).show();

                Marks modal = (Marks) movieList.get(rowPos - 1);
//            float i = Float.parseFloat()

                if(Float.parseFloat(modal.getR_semmark())<Float.parseFloat(mcondition.semmarks) ||
                        Float.parseFloat(modal.getR_avemark())<Float.parseFloat(mcondition.avemarks) ||
                        Integer.parseInt(modal.getR_trainmark())<Integer.parseInt(mcondition.trainmarks)){

                    rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_content_cell_danger);
                    rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_danger);
                    rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_danger);
                    rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_danger);
                    rowViewHolder.txtHi.setBackgroundResource(R.drawable.table_content_cell_danger);

                    rowViewHolder.txtRank.setText(modal.getSid() + "");
                    rowViewHolder.txtMovieName.setText(modal.getS_name());
                    rowViewHolder.txtYear.setText(modal.getR_semmark() + "");
                    rowViewHolder.txtCost.setText(modal.getR_avemark() + "");
                    rowViewHolder.txtHi.setText(modal.getR_trainmark()+ "");

                }else{

                    rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_content_cell_bg);
                    rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_bg);
                    rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_bg);
                    rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_bg);
                    rowViewHolder.txtHi.setBackgroundResource(R.drawable.table_content_cell_bg);

                    rowViewHolder.txtRank.setText(modal.getSid() + "");
                    rowViewHolder.txtMovieName.setText(modal.getS_name());
                    rowViewHolder.txtYear.setText(modal.getR_semmark() + "");
                    rowViewHolder.txtCost.setText(modal.getR_avemark() + "");
                    rowViewHolder.txtHi.setText(modal.getR_trainmark()+ "");
                }

            }

            @Override
            public void onFailure(Call<Mcondition> call, Throwable t) {
                //Toast.makeText(getContext(), "Khong fech duoc"+DataLocalManager.getStringId(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
