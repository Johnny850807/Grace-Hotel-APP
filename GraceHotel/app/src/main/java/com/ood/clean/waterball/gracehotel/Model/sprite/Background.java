package com.ood.clean.waterball.gracehotel.Model.sprite;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.util.Collection;

public class Background extends Sprite {
	private Collection<GameItem> gameItems;
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

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);  // move background
		for (GameItem gameItem : gameItems) // followed by moving all child items
			gameItem.move(dx, dy);
	}
}
