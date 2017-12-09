package com.ood.clean.waterball.gracehotel.Model.sprite;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

public class GameItem extends Sprite {

	public GameItem(ImageSequence imageSequence, SpriteName spriteName) {
		super(imageSequence);
		setSpriteName(spriteName);
	}

	@Override
	public void update() {

	}

}
