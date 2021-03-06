package org.androidtown.lab7;

import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mPlayer;
    int id_audio1;
    int id_audio2;
    String tag1;
    String tag2;
    RelativeLayout layout1;
    RelativeLayout layout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = (RelativeLayout) findViewById(R.id.audio1);
        tag1 = (String) layout1.getTag();
        id_audio1 = getResources().getIdentifier(tag1, "raw", getPackageName());

        layout2 = (RelativeLayout) findViewById(R.id.audio2);
        tag2 = (String) layout2.getTag();
        id_audio2 = getResources().getIdentifier(tag2, "raw", getPackageName());
    }
    public void play(View v) throws IOException {
        if (v.getId() == R.id.audio1) {

            layout1.setBackgroundColor(Color.GRAY);
            layout2.setBackgroundColor(Color.WHITE);

            killMediaPlayer();

            mPlayer = new MediaPlayer();
            AssetFileDescriptor afd = getResources().openRawResourceFd(id_audio1);
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            mPlayer.prepare();
            mPlayer.start();


        } else if (v.getId() == R.id.audio2) {

            layout1.setBackgroundColor(Color.WHITE);
            layout2.setBackgroundColor(Color.GRAY);

            killMediaPlayer();

            mPlayer = new MediaPlayer();
            AssetFileDescriptor afd = getResources().openRawResourceFd(id_audio2);
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            mPlayer.prepare();
            mPlayer.start();
        }
    }

    private void killMediaPlayer() {
        if (mPlayer != null) {
            try {
                mPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }
}

