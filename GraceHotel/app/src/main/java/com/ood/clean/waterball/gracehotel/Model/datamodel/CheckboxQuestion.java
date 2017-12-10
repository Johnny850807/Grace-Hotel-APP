package com.ood.clean.waterball.gracehotel.Model.datamodel;

import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckboxQuestion extends QuestionModel implements Iterable<CheckboxQuestion.Option>{
    private List<Option> options = new ArrayList<>();

    public CheckboxQuestion(int questionId, String question, QuestionType questionType) {
        super(questionId, question, questionType);
    }

    public void addOption(Option option){
        options.add(option);
    }

    @Override
    public Iterator<Option> iterator() {
        return options.iterator();
    }

    public static class Option{
        private String optionName;
        private boolean value;  // checked or not checked

        public Option(String optionName, boolean value) {
            this.optionName = optionName;
            this.value = value;
        }

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        public boolean getValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }
}
