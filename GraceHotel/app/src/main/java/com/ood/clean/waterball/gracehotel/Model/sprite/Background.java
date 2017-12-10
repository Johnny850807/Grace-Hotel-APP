package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Canvas;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.util.ArrayList;
import java.util.Collection;

public class Background extends Sprite {
	private Collection<Sprite> gameItems = new ArrayList<>();
	private int screenWidth;
	private int screenHeight;

	public Background(int width, int height, ImageSequence imageSequence, int screenWidth, int screenHeight) {
		super(width, height, imageSequence);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setSpriteName(SpriteName.BACKGROUND);
	}

	@Override
	public void update() {

	}

	public void tryMove(int dx, int dy){
		int finalX = getX() + dx;
		int finalY = getY() + dy;
		if (finalX <= 0 && finalX + getWidth() >= screenWidth &&
				finalY <= 0 && finalY + getHeight() >= screenHeight)
			move(dx,dy);
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);  // move background
		for (Sprite gameItem : gameItems) // followed by moving all child items
			gameItem.move(dx, dy);
	}

	public void addGameItem(Sprite gameItem){
		this.gameItems.add(gameItem);
	}

	public void removeGameItem(Sprite gameItem){
		this.gameItems.remove(gameItem);
	}

	public void draw(Canvas canvas){
		super.draw(canvas);  // draw background
		for (Sprite sprite : gameItems)
			sprite.draw(canvas);
	}

}
