package com.gigawarestudios.explosiontest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private RelativeLayout rLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Turn off the Title
        requestWindowFeature(Window.FEATURE_NO_TITLE );

        //Set to FullScreen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView( new GamePanel( getApplicationContext() ) );


//        setContentView( R.layout.activity_main );
//
//        rLayout = ( RelativeLayout ) findViewById( R.id.activity_main );
//        rLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                final float x = motionEvent.getX();
//                final float y = motionEvent.getY();
//
//                Toast.makeText(
//                        getApplicationContext(),
//                        "X : " + x + ", Y : " + y,
//                        Toast.LENGTH_SHORT )
//                        .show();
//
//                return true;
//            }
//        });
    }


}
