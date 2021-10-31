package com.example.yadomanage.teacher.teacherflagment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.yadomanage.R;
import com.example.yadomanage.adapter.Mcondition;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentTeacherAdd extends Fragment {
    Mcondition mcondition;
    View view;
    TextView ave,sem,train;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_teacher_add, container, false);

        ave=view.findViewById(R.id.avem);
        sem=view.findViewById(R.id.semm);
        train=view.findViewById(R.id.trainm);
        btn = view.findViewById(R.id.button);
        getCondition();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCondition();
            }
        });

        return view;
    }

    private void getCondition(){
        Call<Mcondition> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showMcondition(Integer.parseInt(DataLocalManager.getStringId()));

        call.enqueue(new Callback<Mcondition>() {
            @Override
            public void onResponse(Call<Mcondition> call, Response<Mcondition> response) {
                 mcondition= response.body();
                //Toast.makeText(getContext(), mcondition.getTrainmarks()+"", Toast.LENGTH_LONG).show();
                ave.setText(mcondition.getAvemarks());
                sem.setText(mcondition.getSemmarks());
                train.setText(mcondition.getTrainmarks());
            }

            @Override
            public void onFailure(Call<Mcondition> call, Throwable t) {
                Toast.makeText(getContext(), "Khong fech duoc"+DataLocalManager.getStringId(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateCondition(){
        float semm = Float.parseFloat(sem.getText().toString());
        float avem = Float.parseFloat(ave.getText().toString());
        int trainm = Integer.parseInt(train.getText().toString());

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .updateCondition(Integer.parseInt(DataLocalManager.getStringId()),avem,semm,trainm);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Toast.makeText(getContext(), "Thiết lập thành công!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Thiết lập thất bại!", Toast.LENGTH_LONG).show();
            }
        });
    }
}