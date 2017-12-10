package com.ood.clean.waterball.gracehotel.Model.sprite;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.MyApplication;
import com.ood.clean.waterball.gracehotel.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpritePrototypeFactory {
	private static final String TAG = "SpritePrototypeFactory";
	private static SpritePrototypeFactory instance;
	private Map<SpriteName, Sprite> spriteMap = new HashMap<>();

	private SpritePrototypeFactory(){}

	public static SpritePrototypeFactory getInstance(){
		if (instance == null)
			instance = new SpritePrototypeFactory();
		return instance;
	}

	public void preparePrototypes() {
		Point size = getScreenSize();
		Bitmap bgBitmap = getBitmap(R.drawable.testbg);
		Log.d(TAG, "Screen Size w:" + size.x + ", h:" + size.y  );
		addPrototype(SpriteName.BACKGROUND, new Background(bgBitmap.getWidth(), bgBitmap.getHeight(), createImageSequence(bgBitmap), size.x, size.y));
	}

	private Point getScreenSize(){
		WindowManager windowManager = (WindowManager) MyApplication.getDefaultContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size;
	}

	private Bitmap getBitmap(@DrawableRes int id){
		Resources resources = MyApplication.getMyResources();
		return BitmapFactory.decodeResource(resources, id);
	}

	private ImageSequence createImageSequence(Bitmap... bitmaps){
		return  new ImageSequence(true, Arrays.asList(bitmaps));
	}

	public void addPrototype(SpriteName name, Sprite sprite) {
		spriteMap.put(name, sprite);
	}

	public Sprite createSprite(SpriteName name) {
		return spriteMap.get(name).clone();
	}

}
