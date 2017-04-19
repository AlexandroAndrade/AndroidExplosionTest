package com.gigawarestudios.explosiontest;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author Alex Andrade ( yngwie_alex@hotmail.com )
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Background background;

    //Size of the Background Image
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    private static final String GAME_PANEL_LOG_TAG = "GamePanel";

    public GamePanel( final Context context ) {
        super( context );

        //Add the Callback to the SurfaceHolder so we can intercept Events
        getHolder().addCallback( this );
        gameThread = new GameThread( getHolder(), this );

        //Make GamePanel focusable
        setFocusable( true );
    }

    public void update() {

        background.update();
    }

    @Override
    public void draw(Canvas canvas) {
        final float scaleFactorX = getWidth() / ( WIDTH * 1.0f )   ;
        final float scaleFactorY = getHeight() / ( HEIGHT * 1.0f ) ;

        if ( canvas != null ) {
            final int savedState = canvas.save();
            canvas.scale( scaleFactorX, scaleFactorY );
            background.draw( canvas );
            canvas.restoreToCount( savedState );
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        background = new Background(
                BitmapFactory.decodeResource( getResources(), R.drawable.mario_background ) );
        background.setVector( 5 );
        gameThread.setRunning( true );
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while ( retry ) {
            try {
                gameThread.setRunning( false );
                gameThread.join();
            } catch ( InterruptedException ie ){
                Log.d( GAME_PANEL_LOG_TAG, "InterruptedException at GameThread.run() : "
                        + ie.getMessage() );
            } catch ( Exception e ) {
                Log.d( GAME_PANEL_LOG_TAG, "Exception at GameThread.run() : "
                        + e.getMessage() );
            }
            retry = false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
