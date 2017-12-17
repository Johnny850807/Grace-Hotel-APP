package com.ood.clean.waterball.gracehotel.View;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.MyApplication;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import com.ood.clean.waterball.gracehotel.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static android.content.ContentValues.TAG;


public class QuestionnaireDialogFragment extends BaseDialogFragment implements QuestionnaireView {
    private static final String QUESTIONS = "questions";
    private static final String USER = "user";
    private QuestionnairePresenter questionnairePresenter;
    private List<QuestionModel> questionModelList ;
    private User user;
    private RadioGroup radioGroup ;
    private RadioGroup radioGroup1 ;
    private QuestionModel questionModel;
    private ArrayList<RadioGroup> listOfRadioGroups = new ArrayList<RadioGroup>();

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

    }


    public void onQuestionnaireLoaded(Questionnaire questionnaire) {
        questionnairePresenter.createModels(questionnaire);
    }

    @Override
    public void onQuestionModelsLoaded(Stack<List<QuestionModel>> questionModels) {
    }

}
