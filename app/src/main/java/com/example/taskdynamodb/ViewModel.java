package com.example.taskdynamodb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private List<NewsDo> list;
    Context context;

    public ViewModel(@NonNull Application application) {
        super(application);
        context = application;
        repository = new Repository(application);
//        list = repository.getAllData(new DatabaseAccess(application).getDynamoDBMapper());
    }

    public List<NewsDo> getAllData() {
        return list;
    }

}
