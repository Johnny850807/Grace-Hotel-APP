package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import com.ood.clean.waterball.gracehotel.R;

import java.util.List;
import java.util.Stack;

/**
 * Created by user on 2017/12/17.
 */

public class MyQuestionnairePanel extends LinearLayout implements QuestionnaireView {
    private QuestionnairePresenter questionnairePresenter;
    private TextView textView;
    private Context context;
    private ProgressBar loadingBar;
    private TextLoadingDecorator textLoadingDecorator;
    private ViewComponentFactory viewComponentFactory;
    private List<QuestionModel> questionModelList;

    public MyQuestionnairePanel(Context context, QuestionnairePresenter questionnairePresenter) {
        super(context);
        this.context = context;
        this.questionnairePresenter = questionnairePresenter;
        this.setOrientation(VERTICAL);
        viewComponentFactory = new ViewComponentFactory(context);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(parms);
        questionnairePresenter.setQuestionnaireView(this);
        questionnairePresenter.loadQuestionnaire();

    }
    private void initView(){
        getRadioGroup();
        getRadioGroup();
        getFilling();
        /*for (QuestionModel q : questionModelList){
            if(q.getQuestionType() == QuestionType.RADIOGROUP)
                getRadioGroup();
            else
                getFilling();

        }*/

    }
    private void getRadioGroup(){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(viewComponentFactory.getTextView(context,"測試問題1"));
        linearLayout.addView(viewComponentFactory.getRadioGroup(context,5));
        addView(linearLayout);
    }
    private void getFilling(){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(viewComponentFactory.getTextView(context,"測試問題1"));
        linearLayout.addView(viewComponentFactory.getEditText(context,"測"));
        addView(linearLayout);
    }
    @Override
    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question) {

    }

    @Override
    public void onAnswerCommittingError(QuestionModel question) {

    }

    @Override
    public void onError(Exception err) {

    }

    @Override
    public void onQuestionnaireLoaded(Questionnaire questionnaire) {
        questionnairePresenter.createModels(questionnaire);
    }

    @Override
    public void onQuestionModelsLoaded(Stack<List<QuestionModel>> questionModels) {
        TextView textView = new TextView(context);
        textView.setText(questionModels.pop().get(0).getQuestion());
        questionModelList = questionModels.pop();

    }
}
