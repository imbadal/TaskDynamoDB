package com.example.taskdynamodb;

import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    Adapter adapter;
    List<NewsDo> list = new ArrayList<>();
    boolean check = true;

    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        scanAllData();


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

    private void scanAllData() {
        new Thread(() -> {
            databaseAccess = new DatabaseAccess(context);
            list = databaseAccess.getAllData();
        }).start();
    }


}
