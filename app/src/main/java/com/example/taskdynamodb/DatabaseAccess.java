package com.example.taskdynamodb;

import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private static final String TAG = "DatabaseAccess";
    private Context context;
    DynamoDBMapper dynamoDBMapper;

    private static volatile DatabaseAccess instance;

    public DatabaseAccess(Context context) {
        this.context = context;

        AWSMobileClient.getInstance().initialize(context).execute();
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
        dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();
    }


    public List<NewsDo> getAllData() {
        PaginatedScanList<NewsDo> newsDos = dynamoDBMapper.scan(NewsDo.class, new DynamoDBScanExpression());
        List<NewsDo> list = new ArrayList<NewsDo>(newsDos);
        return list;
    }

    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseAccess(context);

        return instance;
    }



}
