package krys.threer.system.gui;


import android.content.Intent;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;


import android.widget.VideoView;


import krys.threer.R;
import krys.threer.user.dao.UserSession;
import krys.threer.user.gui.LoginActivity;

public class SplashActivity extends ActionBarActivity {

    private Handler handler;
    private VideoView vidAppgrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       vidAppgrade = (VideoView) findViewById(R.id.vidAppgrade);


        startVideo();

        hideActionBar();



        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startR3();


            }

        }, 5000);

    }

    private void startVideo() {
        Uri uri = Uri.parse("android.resource://krys.threer/raw/appgrade");
        vidAppgrade.setBackground(new ColorDrawable(Color.WHITE));
        vidAppgrade.setVideoURI(uri);
        vidAppgrade.start();

        vidAppgrade.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        vidAppgrade.setBackground(new ColorDrawable(Color.TRANSPARENT));

                    }

                }, 500);

            }
        });


    }


    private void startR3() {
        UserSession session = new UserSession(this);
        //if(session.isUserLoggedIn()) {
           // Intent intent = new Intent(this,SelectCategoryActivity.class);
           // startActivity(intent);

       // }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        //}
    }


    private void hideActionBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }


}
