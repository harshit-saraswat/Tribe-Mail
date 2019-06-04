package com.example.codezero.mysqldb;

/**
 * Created by Harshit Saraswat on 16-03-2018.
 *
 * This class is used to store information regarding every scheme as a complete object.
 */

public class SchemesData {

    String mTags;
    String mName;
    String mDetails;
    int mImgResource;

    public SchemesData(String tags,String name,String details){
        mTags = tags;
        mName= name;
        mDetails = details;
    }

    public SchemesData(String tags,String name,String details,int imgResource){
        mTags = tags;
        mName= name;
        mDetails = details;
        mImgResource=imgResource;
    }

    //Function to get tags.
    public  String getTags(){return mTags;}

    //Function to get name of scheme.
    public  String getName(){return mName;}

    //Function to get Details of scheme.
    public  String getDetails(){return mDetails;}


    //Function to get images of scheme.
    public  int getImageResource(){return mImgResource;}

}
