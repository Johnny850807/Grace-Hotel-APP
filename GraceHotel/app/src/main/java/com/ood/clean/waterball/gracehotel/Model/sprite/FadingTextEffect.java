package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;


public class FadingTextEffect extends Sprite {
    private Paint paint = new Paint();
    private Background background;
    private String text;

    public FadingTextEffect(int width, int height, ImageSequence imageSequence, SpriteName spriteName) {
        super(width, height, imageSequence, spriteName);
        paint.setARGB(255, 255, 255, 255);
        paint.setTextSize(80);
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {
        if (background == null)
            throw new IllegalStateException("the background should be set before update() invoked.");

        setY(getY() - 3);
        paint.setAlpha(paint.getAlpha() - 10 < 0 ? 0 : paint.getAlpha() - 10);
        if(paint.getAlpha() == 0)
            background.removeGameItem(this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, getX(), getY(), paint);
    }
}
