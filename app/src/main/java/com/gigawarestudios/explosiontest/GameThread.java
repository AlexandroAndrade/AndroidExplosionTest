package com.gigawarestudios.explosiontest;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by a.andrade.martinez on 24/02/2017.
 */
public class GameThread extends Thread {

    private int fps = 30;
    private float averageFps;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private volatile boolean running;
    public static Canvas canvas;
    private static final String GAME_THREAD_LOG_TAG = "GameThread";

    public GameThread( SurfaceHolder surfaceHolder, GamePanel gamePanel ) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    @Override
    public void run() {
        long startTime;
        long timeItTook;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1_000 / fps;

        while( running ) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized ( surfaceHolder ) {
                    this.gamePanel.update();
                    this.gamePanel.draw( canvas );

                }
            } catch ( Exception e ) {
                Log.d( GAME_THREAD_LOG_TAG, "Exception at GameThread.run() : "
                        + e.getMessage() );
            } finally {
                if ( canvas != null ) {
                    try {
                        surfaceHolder.unlockCanvasAndPost( canvas );
                    } catch ( Exception e ) {
                        Log.d( GAME_THREAD_LOG_TAG, "Exception at unlockingCanvasAndPost() : "
                                + e.getMessage() );
                    }
                }
            }
			
            timeItTook = ( System.nanoTime() - startTime ) / 1_000_000;
            waitTime = targetTime - timeItTook;

            try {
                if( waitTime > 0L ) {
                    this.sleep( waitTime );
                }
            } catch ( InterruptedException ie ) {
                Log.d( GAME_THREAD_LOG_TAG, "InterruptedException at GameThread.run() : "
                        + ie.getMessage() );
            } catch ( Exception e ) {
                Log.d( GAME_THREAD_LOG_TAG, "Exception at GameThread.run() : "
                        + e.getMessage() );
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if( frameCount == fps ) {
                averageFps = 1_000 / ( ( totalTime / frameCount ) / 1_000_000 ) ;
                frameCount = 0;
                totalTime = 0;
                Log.d( GAME_THREAD_LOG_TAG, "Average FPS -> " + averageFps );
            }


        }

    }


    public void setRunning(boolean running) {
        this.running = running;
    }
}














