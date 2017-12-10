package com.ood.clean.waterball.gracehotel.Model.datamodel;

import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

public abstract class QuestionModel {
	protected int questionId;
	protected String question;
	protected QuestionType questionType;

	public QuestionModel(int questionId, String question, QuestionType questionType) {
		this.questionId = questionId;
		this.question = question;
		this.questionType = questionType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
}
