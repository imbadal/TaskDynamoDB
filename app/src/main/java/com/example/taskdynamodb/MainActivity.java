package com.example.taskdynamodb;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private Repository repository;
    DynamoDBMapper dynamoDBMapper;
    RecyclerView recyclerView;
    Adapter adapter;
    List<NewsDo> list = new ArrayList<>();
    boolean check = true;

    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
//        list = viewModel.getAllData();


        repository = new Repository();
        dynamoDBMapper = new DatabaseAccess(this).getDynamoDBMapper();
        getAllData();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(list);
        Log.d("check_list", "onCreate: " + list.size());
        recyclerView.setAdapter(adapter);

        while (check) {
            if (list.size() > 0) {
                Collections.sort(list, (o1, o2) -> {
                    if (o1.getTimeStamp() - o2.getTimeStamp() < 0)
                        return 1;
                    else if (o1.getTimeStamp() - o2.getTimeStamp() > 0)
                        return -1;
                    else
                        return 0;
                });

                adapter = new Adapter(list);
                recyclerView.setAdapter(adapter);
                check = false;
            }
        }
    }

    public void getAllData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list=  repository.getAllData(dynamoDBMapper);
            }
        }).start();
    }

}
