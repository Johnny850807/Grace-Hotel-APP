package com.ood.clean.waterball.gracehotel.Model.sprite;


import android.content.Context;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.util.HashMap;
import java.util.Map;

public class SpritePrototypeFactory {
	private static SpritePrototypeFactory instance;
	private Map<SpriteName, Sprite> spriteMap = new HashMap<>();

	private SpritePrototypeFactory(){}

	public static SpritePrototypeFactory getInstance(){
		if (instance == null)
			instance = new SpritePrototypeFactory();
		return instance;
	}

	private void preparePrototypes(Context context) {

	}

	public void addPrototype(SpriteName name, Sprite sprite) {

	}

	public Sprite createSprite(SpriteName name) {
		return null;
	}

}
