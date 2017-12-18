package com.ood.clean.waterball.gracehotel.Model.sprite;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;

public class GameItem extends Sprite {

	public GameItem(int width, int height, ImageSequence imageSequence, SpriteName spriteName, SpriteProxy spriteProxy) {
		super(width, height, imageSequence, spriteName, spriteProxy);
	}

	@Override
	public void update() {

	}

}
