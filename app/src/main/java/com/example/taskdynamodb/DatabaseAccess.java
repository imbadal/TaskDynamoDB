package com.example.taskdynamodb;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.ScanOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Search;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private final String COGNITO_IDENTITY_POOL_ID = "ap-south-1:60314d52-74b2-4a50-92e2-ea4828bc8775";
    private final Regions COGNITO_IDENTITY_POOL_REGION = Regions.AP_SOUTH_1;
    private final String DYNAMODB_TABLE = "riyaz_dev_submissions_for_review";

    private Context context;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;

    private static volatile DatabaseAccess instance;

    public DatabaseAccess(Context context) {
        this.context = context;

        credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                COGNITO_IDENTITY_POOL_ID,
                COGNITO_IDENTITY_POOL_REGION
        );

        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(COGNITO_IDENTITY_POOL_REGION));
        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);
    }


    public List<Document> getAllData() {

        ScanOperationConfig scanConfig = new ScanOperationConfig();
        List<String> attributeList = new ArrayList<>();
        attributeList.add("userId");
        attributeList.add("timeStamp");
        attributeList.add("title");
        attributeList.add("isReviewed");
        scanConfig.withAttributesToGet(attributeList);
        Search searchResult = dbTable.scan(scanConfig);
        return searchResult.getAllResults();
    }

    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseAccess(context);

        return instance;
    }

}
