package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Canvas;
import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Background extends Sprite implements Iterable<Sprite>{
	private static final String TAG = "Background";
	private Collection<Sprite> gameItems = Collections.synchronizedList(new ArrayList<>());
	private Mask mask;
	private int screenWidth;
	private int screenHeight;

	public Background(int width, int height, ImageSequence imageSequence, int screenWidth, int screenHeight) {
		super(width, height, imageSequence, SpriteName.BACKGROUND);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.mask = new Mask(screenWidth, screenHeight, null, SpriteName.MASK);
	}

	@Override
	public synchronized void update() {
		try{
			for (Sprite gameItem : gameItems)
				gameItem.update();
			mask.update();
		}catch (ConcurrentModificationException err){}
	}

	public void tryMove(int dx, int dy){
		int finalX = getX() + dx;
		int finalY = getY() + dy;
		if (finalX <= 0 && finalX + getWidth() >= screenWidth &&
				finalY <= 0 && finalY + getHeight() >= screenHeight)
			move(dx,dy);
		Log.d(TAG, "Background: ("+getX()+","+getY()+")");
	}

	@Override
	public synchronized void move(int dx, int dy) {
		super.move(dx, dy);  // move background
		try{
			for (Sprite gameItem : gameItems)
				gameItem.move(dx, dy);  // followed by moving all child items
		}catch (ConcurrentModificationException err){}
	}

	public synchronized void addGameItem(Sprite gameItem){
		this.gameItems.add(gameItem);
	}

	public synchronized void removeGameItem(Sprite gameItem){
		this.gameItems.remove(gameItem);
	}

	public synchronized void draw(Canvas canvas){
		super.draw(canvas);  // draw background
		try{
			for (Sprite sprite : gameItems)
				sprite.draw(canvas);
			mask.draw(canvas);
		}catch (ConcurrentModificationException err){}
	}

	public void arrangeItemRandomly(List<Sprite> sprites){
		Random random = new Random();
		for(Sprite sprite : sprites)
		{
			int randomX = random.nextInt(getWidth() + 1) + getX() - sprite.getWidth();  //to avoid out of map
			int randomY = random.nextInt(getHeight() + 1) + getY() - sprite.getHeight();
			sprite.setX(randomX);
			sprite.setY(randomY);
			addGameItem(sprite);
		}
	}

	@Override
	public Iterator<Sprite> iterator() {
		return gameItems.iterator();
	}

	public int size(){
        return gameItems.size();
    }
}
