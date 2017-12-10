package com.ood.clean.waterball.gracehotel.Presenter;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Model.sprite.SpritePrototypeFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GamePresenter {
	private SpritePrototypeFactory prototypeFactory;
	private Sprite background;
	private List<Sprite> gameItems = new ArrayList<>();
	private List<Delta> unhandleScrollRequesnt = new LinkedList<Delta>();
	public GamePresenter(){

	}

	public void gameStart() {
		background = prototypeFactory.createSprite(SpriteName.BACKGROUND);

	}

	public void scrollBackground(int dx, int dy) {
		unhandleScrollRequesnt.add(new Delta(dx, dy));
	}

	public void executeItemEffect(Sprite gameItem) {

	}

	public void fillQuestion(QuestionModel questionModel) {

	}



	private static class Delta{
		int x;
		int y;

		public Delta(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
