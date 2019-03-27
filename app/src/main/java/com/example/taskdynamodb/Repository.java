package com.example.taskdynamodb;

import android.app.Application;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private Dao dao;
    List<NewsDo> list;

    public Repository(Application application) {

        DatabaseAccess databaseAccess = new DatabaseAccess(application).getInstance(application);
        list = dao.getAllData();
    }

    public List<NewsDo> getAllData(DynamoDBMapper dynamoDBMapper) {

        PaginatedScanList<NewsDo> newsDos = dynamoDBMapper.scan(NewsDo.class, new DynamoDBScanExpression());
        list = new ArrayList<NewsDo>(newsDos);
        return list;

    }


}
