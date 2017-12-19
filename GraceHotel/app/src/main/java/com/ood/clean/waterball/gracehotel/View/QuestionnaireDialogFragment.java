package com.ood.clean.waterball.gracehotel.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.MyApplication;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import com.ood.clean.waterball.gracehotel.R;

import java.util.LinkedList;


public class QuestionnaireDialogFragment extends BaseDialogFragment implements QuestionnaireView {
    private static final String QUESTIONS = "questions";
    private static final String USER = "user";
    private QuestionnairePresenter questionnairePresenter;
    private MyQuestionnairePanel questionnaireViewGroup;
    private TextLoadingDecorator textLoadingDecorator;
    private User user;
    private ProgressBar loadingBar;


    //required empty constructor
    public QuestionnaireDialogFragment(){}

    public static QuestionnaireDialogFragment newInstance(User user){
        QuestionnaireDialogFragment fragment = new QuestionnaireDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable(USER);

        questionnairePresenter = new QuestionnairePresenter(MyApplication.getThreadExecutor(), user, MyApplication.getUserRepository(),
                MyApplication.getQuestionnaireRepository(), MyApplication.getLanguage());

    }

    @Override
    protected View createView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_questionnaire , null);
        questionnairePresenter.setQuestionnaireView(this);
        questionnairePresenter.loadQuestionnaire();
        questionnaireViewGroup = new MyQuestionnairePanel(mView.getContext(),questionnairePresenter);
        loadingBar = mView.findViewById(R.id.loadingBar);
        loadingBar.setVisibility(mView.VISIBLE);
        new Thread(textLoadingDecorator).start();
        return mView;  //TODO
    }

    @Override
    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question) {
    }

    @Override
    public void onAnswerCommittingError(QuestionModel question) {

    }

    @Override
    public void onError(Exception err) {
        err.printStackTrace();
        loadingBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onQuestionnaireLoaded(Questionnaire questionnaire) {

    }


    @Override
    public void onQuestionModelsLoaded(LinkedList<QuestionGroupModel> questionModelList) {

    }



}
