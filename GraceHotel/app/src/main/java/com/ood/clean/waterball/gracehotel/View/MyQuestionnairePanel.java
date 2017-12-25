package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import java.util.LinkedList;


public class MyQuestionnairePanel extends LinearLayout implements QuestionnaireView {
    private QuestionnairePresenter questionnairePresenter;
    private Context context;
    private ViewComponentFactory viewComponentFactory;

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
    }
    private void initView(QuestionGroupModel questionGroupModel){
        for (QuestionModel q : questionGroupModel){
            if(q.getQuestionType() == QuestionType.RADIOGROUP)
                getRadioGroup();
            else
                getFilling();
        }
    }
    private void getRadioGroup(){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(viewComponentFactory.getTextView(context,"測試問題"));
        linearLayout.addView(viewComponentFactory.getRadioGroup(context,5));
        addView(linearLayout);
    }
    private void getFilling(){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(viewComponentFactory.getTextView(context,"測試意見填寫區"));
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
        //initView();
        questionnairePresenter.createModels(questionnaire);
    }

    @Override
    public void onQuestionModelsLoaded(LinkedList<QuestionGroupModel> questionModelList) {
        //initView(questionModelList.getFirst());
        TextView textView = new TextView(context);
        textView.setText(questionModelList.getFirst().getTitle());
        addView(textView);
        for (QuestionModel q : questionModelList.getFirst()){
            TextView textView1 = new TextView(context);
            textView1.setText(q.getQuestion());
            addView(textView1);
        }


    }

}
