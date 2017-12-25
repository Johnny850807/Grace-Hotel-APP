package com.ood.clean.waterball.gracehotel.Model.datamodel;


import java.util.Iterator;
import java.util.List;

public class QuestionGroupModel implements Iterable<QuestionModel>{
    private int questionGroupId;
    private int part;
    private String title;
    private List<QuestionModel> questionModels;

    public QuestionGroupModel(int questionGroupId, int part, String title, List<QuestionModel> questionModels) {
        this.questionGroupId = questionGroupId;
        this.part = part;
        this.title = title;
        this.questionModels = questionModels;
    }

    @Override
    public Iterator<QuestionModel> iterator() {
        return questionModels.iterator();
    }

    public int getQuestionGroupId() {
        return questionGroupId;
    }

    public void setQuestionGroupId(int questionGroupId) {
        this.questionGroupId = questionGroupId;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }
}
