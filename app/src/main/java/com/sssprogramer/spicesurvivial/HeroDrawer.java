package com.sssprogramer.spicesurvivial;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Степаашка on 12.04.2017.
 */

public class HeroDrawer extends Drawer<Player> {
    public HeroDrawer(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,model.pos.x-bitmap.getWidth()/2,model.pos.y-bitmap.getHeight()/2,null);
    }

    private Bitmap bitmap;
}
