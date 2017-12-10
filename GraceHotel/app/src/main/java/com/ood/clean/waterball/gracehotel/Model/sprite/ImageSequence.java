package com.ood.clean.waterball.gracehotel.Model.sprite;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.util.List;

public class ImageSequence implements Cloneable{
	private int position = 0;
	private boolean cycle = true;
	private List<Bitmap> images;

	public ImageSequence(boolean cycle, List<Bitmap> images) {
		this.cycle = cycle;
		this.images = images;
	}

	public @Nullable Bitmap next() {
		return position != images.size() ? images.get(position ++) :
								isCycle() ? images.get(position = 0) : null;
	}

	public boolean isCycle() {
		return cycle;
	}

	public void setCycle(boolean cycle) {
		this.cycle = cycle;
	}

	public ImageSequence clone(){
		try {
			return (ImageSequence) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
