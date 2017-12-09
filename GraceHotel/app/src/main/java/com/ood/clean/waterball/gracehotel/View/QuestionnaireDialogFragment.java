package com.ood.clean.waterball.gracehotel.View;

import android.os.Bundle;
import android.view.View;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;

import java.util.ArrayList;


public class QuestionnaireDialogFragment extends BaseDialogFragment implements QuestionnaireView {
    private static final String QUESTIONS = "questions";
    private ArrayList<QuestionModel> questionModels;

    //required empty constructor
    public QuestionnaireDialogFragment(){}

    public static QuestionnaireDialogFragment newInstance(ArrayList<QuestionModel> questionModels){
        QuestionnaireDialogFragment fragment = new QuestionnaireDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTIONS, questionModels);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionModels = (ArrayList<QuestionModel>) getArguments().getSerializable(QUESTIONS);
    }

    @Override
    protected View createView() {
        return null;  //TODO
    }

    @Override
    public void onAnswerFillingSuccessfully(QuestionModel filledQuestion) {

    }

    @Override
    public void onQuestionnaireCommitedSuccessfully() {

    }

    @Override
    public void onQuestionnaireCommitedFailed() {

    }
}
