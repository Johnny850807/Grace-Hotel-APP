package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.R;

/**
 * Created by user on 2018/1/10.
 */

public class MyAlertDialog extends AlertDialog implements View.OnClickListener{
    private Context context;
    private View view;
    private EditText edtAge;
    private EditText edtVisitTimes;
    private EditText edtEmail;
    private EditText edtNationality;
    private Button btnSubmit;
    private User user;

    public MyAlertDialog(Context context,User user) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_inputuserinformation, null);
        this.context = context;
        this.user = user;
        this.setView(view);
        initView();
    }
    private void initView(){
        edtAge = (EditText) view.findViewById(R.id.UserAge);
        edtVisitTimes = (EditText) view.findViewById(R.id.UserVisitTimes);
        edtEmail = (EditText) view.findViewById(R.id.UserEmail);
        edtNationality = (EditText) view.findViewById(R.id.UserNationality);
        btnSubmit = (Button) view.findViewById(R.id.UserSubmit);
        btnSubmit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        boolean edtEmailAvailable = edtEmail.getText().toString().length() > 0;
        boolean edtAgeAvailable = edtAge.getText().toString().length() > 0;
        boolean edtNationalityAvailable = edtNationality.getText().toString().length() > 0;
        boolean edtVisitTimesAvailable = edtVisitTimes.getText().toString().length() > 0;
        boolean result = edtAgeAvailable && edtEmailAvailable  && edtNationalityAvailable && edtVisitTimesAvailable;
        if(result){
            user.setAge(Integer.valueOf(edtAge.getText().toString()));
            user.setEmail(edtEmail.getText().toString());
            user.setGraceAmount(Integer.valueOf(edtVisitTimes.getText().toString()));
            user.setNation(edtNationality.getText().toString());
            user.setHasComeToGrace(true);
            dismiss();
        }
        else{
            Toast.makeText(context, R.string.pleaseFulfill, Toast.LENGTH_LONG).show();
        }
    }
}
