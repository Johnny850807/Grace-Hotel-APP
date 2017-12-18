package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;


public class Mask extends Sprite {
    private final static int INCREASING = 0;
    private final static int DECREASING = 1;
    private final static byte INCREASE_NUM = 8;
    private final static byte DECREASE_NUM = 15;
    private int waitingCountdown = 18;
    private byte status = INCREASING;  // increasing followed by waiting a moment then start deceasing finally end up with 0 alpha forever
    private Rect rect = new Rect(0, 0, 2000, 2000);
    private Paint paint = new Paint();

    public Mask(int width, int height, ImageSequence imageSequence, SpriteName spriteName) {
        super(width, height, imageSequence, spriteName);
        paint.setARGB(150, 255, 255, 255);
    }

    @Override
    public void update() {
        int alpha = paint.getAlpha();
        if (status == INCREASING)
        {
            paint.setAlpha(alpha + INCREASE_NUM > 255 ? 255 : alpha + INCREASE_NUM);
            if (alpha  >= 255)
                if (waitingCountdown-- < 0)  //wait a moment in the full white
                    status = DECREASING;
        }
        else if (alpha > 0)
            paint.setAlpha(alpha - DECREASE_NUM < 0 ? 0 : alpha - DECREASE_NUM);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }
}
