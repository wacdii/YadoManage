package com.example.yadomanage.chat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.sharedPreferences.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity {
    String sid, sname;
    RecyclerView recyclerView;
    TextView editTextMessage;
    Button buttonSend;
    ThreadAdapter threadAdapter;
    Toolbar toolbar;
//    ArrayList<Message> messages = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            sid = bundle.getString("Key_1", "");
            sname = bundle.getString("Key_2", "");

        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("  "+sname+" - "+sid);
        getMessage();


//
//        messages.add(new Message("Hello EM",java.time.LocalDateTime.now()+" ",sid,DataLocalManager.getStringId(),"t"));
//        messages.add(new Message("Hello Co",java.time.LocalDateTime.now()+" ",sid,DataLocalManager.getStringId(),"s"));
//        messages.add(new Message("Hello EM2",java.time.LocalDateTime.now()+" ",sid,DataLocalManager.getStringId(),"t"));
//        messages.add(new Message("Hello Co2",java.time.LocalDateTime.now()+" ",sid,DataLocalManager.getStringId(),"s"));
//        threadAdapter =new ThreadAdapter(getApplicationContext(), (ArrayList<Message>) messages,DataLocalManager.getStringId());
//        recyclerView.setAdapter(threadAdapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mess = editTextMessage.getText().toString();
                addMessage(mess);
                getMessage();
                //recyclerView.setAdapter(threadAdapter);
//                messages.add(new Message(mess,java.time.LocalDateTime.now()+"",sid,DataLocalManager.getStringId(),"t"));
//                recyclerView.setAdapter(threadAdapter);
//                editTextMessage.setText("");

            }
        });
    }
    public void getMessage(){
        Call<List<Message>> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showMessage(Integer.parseInt(DataLocalManager.getStringId()),sid);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
//                Toast.makeText(getApplication(),response.code()+"",Toast.LENGTH_SHORT).show();
//                List<Message> messages1 = ;
                threadAdapter =new ThreadAdapter(getApplicationContext(), (ArrayList<Message>) response.body(),DataLocalManager.getStringId());
                recyclerView.setAdapter(threadAdapter);

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(getApplication(),"???",Toast.LENGTH_SHORT).show();
            }
        });

        ;
    }

    public void addMessage(String mess){
        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .addMessage(mess,sid,Integer.parseInt(DataLocalManager.getStringId()),"t");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                editTextMessage.setText("");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplication(),"???",Toast.LENGTH_SHORT).show();
            }
        });

    }
}