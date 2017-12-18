package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Canvas;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.NullProxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Background extends Sprite implements Iterable<Sprite>{
	private Collection<Sprite> gameItems = Collections.synchronizedList(new ArrayList<>());
	private int screenWidth;
	private int screenHeight;

	public Background(int width, int height, ImageSequence imageSequence, int screenWidth, int screenHeight) {
		super(width, height, imageSequence, SpriteName.BACKGROUND, new NullProxy(SpriteName.BACKGROUND));
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
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
		for (Sprite gameItem : gameItems)
			gameItem.move(dx, dy);  // followed by moving all child items
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

	@Override
	public Iterator<Sprite> iterator() {
		return gameItems.iterator();
	}
}
