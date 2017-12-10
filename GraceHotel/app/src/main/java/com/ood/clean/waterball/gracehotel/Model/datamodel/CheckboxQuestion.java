package com.ood.clean.waterball.gracehotel.Model.datamodel;

import java.util.ArrayList;
import java.util.List;

public class CheckboxQuestion extends QuestionModel {
    private List<Option> options = new ArrayList<>();

    public CheckboxQuestion(int questionId, String question) {
        super(questionId, question);
    }

    public void addOption(Option option){
        options.add(option);
    }

    public class Option{
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

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }
}
