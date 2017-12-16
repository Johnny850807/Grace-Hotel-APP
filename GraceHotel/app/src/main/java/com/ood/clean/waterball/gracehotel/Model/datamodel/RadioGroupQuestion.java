package com.ood.clean.waterball.gracehotel.Model.datamodel;

import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RadioGroupQuestion extends QuestionModel implements Iterable<RadioGroupQuestion.Option>{
    private List<Option> options = new ArrayList<>();

    public RadioGroupQuestion(int questionGroupId, int questionId, String question, QuestionType questionType) {
        super(questionGroupId, questionId, question, questionType);
    }


    public void addOption(Option option){
        options.add(option);
    }

    @Override
    public Iterator<Option> iterator() {
        return options.iterator();
    }

    public List<Option> getOptions() {
        return options;
    }

    public Option getOption(int index){
        return options.get(index);
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
