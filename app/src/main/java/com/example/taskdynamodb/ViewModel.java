package com.example.taskdynamodb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import android.content.Context;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

import androidx.annotation.NonNull;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private List<NewsDo> list;
    DynamoDBMapper dynamoDBMapper;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        dynamoDBMapper = new DatabaseAccess(application).getDynamoDBMapper();
//        list = repository.getAllData(new DatabaseAccess(application).getDynamoDBMapper());
    }

    public List<NewsDo> getAllData() {
        return repository.getAllData(dynamoDBMapper);
    }

}
