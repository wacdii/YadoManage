package com.example.yadomanage.teacher.classfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.newsfeed.NewfeedAdapter;
import com.example.yadomanage.newsfeed.Newsfeed;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.sharedPreferences.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentClassNewfeed extends Fragment {
    RecyclerView recyclerView;
    TextView editTextMessage;
    Button buttonSend;
    NewfeedAdapter newfeedAdapter;
    View view;
    ArrayList<Newsfeed> newsfeeds = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_class_newfeed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        editTextMessage = view.findViewById(R.id.editTextMessage);
        buttonSend = view.findViewById(R.id.buttonSend);

       buttonSend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getNewfeed();
           }
       });

        newsfeeds.add(new Newsfeed("Tuần sau chúng ta họp cố vấn nhé các em!","20:20 - 31-10-2021","7777","DI"));
        newsfeeds.add(new Newsfeed("Tuần sau chúng ta đi ăn chè bưởi nhé các em!","20:20 - 31-10-2021","7777","DI"));
        newsfeeds.add(new Newsfeed("Tuần sau chúng ta đi bar nhé các em!","20:20 - 31-10-2021","7777","DI"));
        newfeedAdapter = new NewfeedAdapter(getContext(), (ArrayList<Newsfeed>) newsfeeds);
        recyclerView.setAdapter(newfeedAdapter);


        return view;
    }

    private void getNewfeed(){
        int mgv = Integer.parseInt(DataLocalManager.getStringId());
        String ml ="DI18V7F2";

        Log.d("===",mgv+"");

        Call<List<Newsfeed>> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showNewfeed(mgv,ml);

        call.enqueue(new Callback<List<Newsfeed>>() {
            @Override
            public void onResponse(Call<List<Newsfeed>> call, Response<List<Newsfeed>> response) {
                Toast.makeText(getContext(),response.code()+"",Toast.LENGTH_SHORT).show();
//                newfeedAdapter = new NewfeedAdapter(getContext(), (ArrayList<Newsfeed>) response.body());
//                recyclerView.setAdapter(newfeedAdapter);
            }

            @Override
            public void onFailure(Call<List<Newsfeed>> call, Throwable t) {
                Toast.makeText(getContext(),"???",Toast.LENGTH_SHORT).show();
            }
        });
    }
}