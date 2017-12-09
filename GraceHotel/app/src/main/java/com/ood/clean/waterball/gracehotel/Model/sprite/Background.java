package com.ood.clean.waterball.gracehotel.Model.sprite;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.util.Collection;

public class Background extends Sprite {

	private Collection<GameItem> gameItem;

	public Background(ImageSequence imageSequence) {
		super(imageSequence);
		setSpriteName(SpriteName.BACKGROUND);
	}

	@Override
	public void update() {

	}

}
