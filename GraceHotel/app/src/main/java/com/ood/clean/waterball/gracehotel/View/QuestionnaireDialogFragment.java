package com.ood.clean.waterball.gracehotel.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.MyApplication;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import com.ood.clean.waterball.gracehotel.R;


public class QuestionnaireDialogFragment extends BaseDialogFragment {
    private static final String TAG = "QuestionnaireView";
    private static final String QUESTIONS = "questions";
    private static final String USER = "user";
    private QuestionnairePresenter questionnairePresenter;
    private MyQuestionnairePanel myquestionnairepanel;
    private User user;
    private ProgressBar loadingBar;
    private Button submitBtn;


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
        submitBtn = mView.findViewById(R.id.dialog_questionnaire_btn);
        myquestionnairepanel = new MyQuestionnairePanel(getActivity(), questionnairePresenter, this);
        LinearLayout parent = mView.findViewById(R.id.mylayout);
        parent.addView(myquestionnairepanel);


        submitBtn.setOnClickListener((v)-> {
                if(myquestionnairepanel.checkAndCommitRespone())
                    dismiss();
                else
                    Toast.makeText(getActivity(), getString(R.string.pleaseFulfill),Toast.LENGTH_LONG).show();
            }
        );
        return mView;  //TODO
    }

}