package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Степаашка on 11.04.2017.
 */

public class StarDrawer extends Drawer<Star> {
    public StarDrawer( Paint style) {
        this.style=style;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(model.pos.x,model.pos.y,model.size,style);
    }
    private Paint style;
}
