package com.ood.clean.waterball.gracehotel.Model.entity;

public class Question {
	private int id;
	private String question;
	private String optionsXml;
	private QuestionType questionType;

	public Question(int id, String question, String optionsXml, QuestionType questionType) {
		this.id = id;
		this.question = question;
		this.optionsXml = optionsXml;
		this.questionType = questionType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionsXml() {
		return optionsXml;
	}

	public void setOptionsXml(String optionsXml) {
		this.optionsXml = optionsXml;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
}
