package com.ood.clean.waterball.gracehotel.Model.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Questionnaire implements Iterable<QuestionGroup>{
	private int id;
	private Date createdDate;
	private String name;
	private List<QuestionGroup> questionGroups;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QuestionGroup> getQuestionGroups() {
		return questionGroups;
	}

	public void setQuestionGroups(List<QuestionGroup> questionGroups) {
		this.questionGroups = questionGroups;
	}

	@Override
	public Iterator<QuestionGroup> iterator() {
		return questionGroups.iterator();
	}
}
