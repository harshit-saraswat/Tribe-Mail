package com.example.codezero.mysqldb;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VidPlayer extends AppCompatActivity {

    Button playBtn;
    VideoView vidView;
    String vid;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vid_player);
        vid= getIntent().getExtras().getString("lang");
        playBtn=(Button)findViewById(R.id.playBtn);
        vidView=(VideoView)findViewById(R.id.vidView);
        mediaC=new MediaController(this);
    }

    public void playVid(View view){

        String videoPath1="android.resource://com.example.codezero.mysqldb/"+R.raw.english;
        String videoPath2="android.resource://com.example.codezero.mysqldb/"+R.raw.dogri;
        Uri uri;
        if (vid.contains("English")){
            uri=Uri.parse(videoPath1);
        }else{
            uri=Uri.parse(videoPath2);
        }

        vidView.setVideoURI(uri);
        vidView.setMediaController(mediaC);
        mediaC.setAnchorView(vidView);
        vidView.start();
    }
}
