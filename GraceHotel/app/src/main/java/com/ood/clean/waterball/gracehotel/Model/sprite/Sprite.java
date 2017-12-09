package com.ood.clean.waterball.gracehotel.Model.sprite;

public class Sprite implements Cloneable{

	private int x;
	private int y;

	private ImageSequence imageSequence;

	public Sprite(ImageSequence imageSequence) {
		this.imageSequence = imageSequence;
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

	public ImageSequence getImageSequence() {
		return imageSequence;
	}

	public void setImageSequence(ImageSequence imageSequence) {
		this.imageSequence = imageSequence;
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
}
