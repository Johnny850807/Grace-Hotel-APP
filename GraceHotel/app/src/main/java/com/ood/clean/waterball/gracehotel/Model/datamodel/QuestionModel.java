package com.ood.clean.waterball.gracehotel.Model.datamodel;

public abstract class QuestionModel {
	protected int questionId;
	protected String question;

	public QuestionModel(int questionId, String question) {
		this.questionId = questionId;
		this.question = question;
	}
}
