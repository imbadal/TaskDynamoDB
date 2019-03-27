package com.example.taskdynamodb;

import android.app.Application;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;

import java.util.ArrayList;
import java.util.List;

public class Repository {


    public Repository() {
    }

    public List<NewsDo> getAllData(DynamoDBMapper dynamoDBMapper) {

        PaginatedScanList<NewsDo> newsDos = dynamoDBMapper.scan(NewsDo.class, new DynamoDBScanExpression());
        return new ArrayList<NewsDo>(newsDos);

    }
}
