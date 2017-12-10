package com.ood.clean.waterball.gracehotel.Model.datamodel;

import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

public class FillingQuestion extends QuestionModel {
    private String hint = "";
    private String answer;

    public FillingQuestion(int questionId, String question, QuestionType questionType) {
        super(questionId, question, questionType);
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
