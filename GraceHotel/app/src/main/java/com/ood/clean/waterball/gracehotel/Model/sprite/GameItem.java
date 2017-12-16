package com.ood.clean.waterball.gracehotel.Model.sprite;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.EventHandler;

public class GameItem extends Sprite {

	public GameItem(int width, int height, ImageSequence imageSequence, SpriteName spriteName, EventHandler eventHandler) {
		super(width, height, imageSequence, eventHandler);
		setSpriteName(spriteName);
	}

	@Override
	public void update() {

	}

}
