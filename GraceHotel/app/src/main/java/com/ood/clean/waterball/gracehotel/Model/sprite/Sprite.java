package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;

public class Sprite implements Cloneable{
	private int x;
	private int y;
	private int width;
	private int height;
	private SpriteName spriteName;
	private ImageSequence imageSequence;
	private SpriteProxy spriteProxy;

	public Sprite(int width, int height, ImageSequence imageSequence, SpriteName spriteName, SpriteProxy spriteProxy) {
		this.width = width;
		this.height = height;
		this.imageSequence = imageSequence;
		this.spriteName = spriteName;
		this.spriteProxy = spriteProxy;
	}

	public void update() {
		//do nothing as default
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public SpriteName getSpriteName() {
		return spriteName;
	}

	public void setSpriteName(SpriteName spriteName) {
		this.spriteName = spriteName;
	}

	public ImageSequence getImageSequence() {
		return imageSequence;
	}

	public void setImageSequence(ImageSequence imageSequence) {
		this.imageSequence = imageSequence;
	}

	public Bitmap nextBitmap(){
		return imageSequence.next();
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void move(int dx, int dy){
		setX(getX() + dx);
		setY(getY() + dy);
	}

	public void draw(Canvas canvas){
		Bitmap bitmap = nextBitmap();
		if (bitmap != null && canvas != null)
			canvas.drawBitmap(nextBitmap(), getX(), getY(), null);
	}

	public Sprite clone(){
		try {
			Sprite sprite = (Sprite) super.clone();
			sprite.setImageSequence(imageSequence.clone());
			return sprite;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isTouched(int x, int y){
		return x >= this.getX() && x <= this.getX() + getWidth() &&
				y >= this.getY() && y <= this.getY() + getHeight();
	}

	public SpriteProxy getSpriteProxy() {
		return spriteProxy;
	}
}
