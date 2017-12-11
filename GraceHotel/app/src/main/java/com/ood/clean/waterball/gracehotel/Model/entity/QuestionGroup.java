package com.ood.clean.waterball.gracehotel.Model.entity;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionGroup implements Iterable<Question>{
    private int id;
    private int partNumber;
    private String name;
    private List<Question> questions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }
}
