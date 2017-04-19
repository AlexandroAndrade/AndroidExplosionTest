package com.gigawarestudios.explosiontest;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by a.andrade.martinez on 24/02/2017.
 */
public class Background {

    private Bitmap bitmap;
    private int x;
    private int y;
    private int dx;

    public Background( Bitmap b ) {
        this.bitmap = b;
    }

    public void update() {
        x -= dx;
        if( x < -GamePanel.WIDTH ) {
            x = 0;
        }
    }

    public void draw( Canvas canvas ) {
        canvas.drawBitmap( bitmap, x, y, null );
        if ( x < 0 ) {
            canvas.drawBitmap( bitmap, x + GamePanel.WIDTH, y, null );
        }
    }

    public void setVector( int dx ) {
        this.dx = dx;
    }
}
