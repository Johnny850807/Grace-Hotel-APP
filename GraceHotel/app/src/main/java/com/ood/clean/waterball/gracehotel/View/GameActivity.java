package com.ood.clean.waterball.gracehotel.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.Permissions;
import com.ood.clean.waterball.gracehotel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements GameParentView{
    private final static String TAG = "GameActivity";
    private User user;
    @BindView(R.id.icon1) ImageButton icon1Btn;
    @BindView(R.id.icon2) ImageButton icon2Btn;
    @BindView(R.id.icon3) ImageButton icon3Btn;
    @BindView(R.id.surveyBtn) ImageButton surveyBtn;
    @BindView(R.id.container) RelativeLayout container;
    @BindView(R.id.roomNumberTxt) TextView roomNumberTxt;
    @BindView(R.id.moneyTxt) TextView moneyTxt;
    @BindView(R.id.graceTxt) TextView graceTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        setupViews();
        Log.d(TAG, "GameSurfaceView added");
    }


    private void init(){
        user = (User) getIntent().getSerializableExtra("User");
    }

    @SuppressLint("SetTextI18n")
    private void setupViews(){
        initAndAddGameSurfaceView();
        setupIconButtons();
        roomNumberTxt.setText(user.getRoomNumber());  // spaces for margin left
        moneyTxt.setText(String.valueOf(user.getMoney()) + "$");
        graceTxt.setText(String.valueOf(user.getGraceAmount()) + getString(R.string.unit));
    }

    private void initAndAddGameSurfaceView(){
        GameSurfaceView gameSurfaceView = new GameSurfaceView(this, user, this);
        gameSurfaceView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(gameSurfaceView);
    }

    private void setupIconButtons(){

        Glide.with(this).load(R.drawable.equipmenticon).fitCenter().into(icon1Btn);
        Glide.with(this).load(R.drawable.souveniricon).fitCenter().into(icon2Btn);
        Glide.with(this).load(R.drawable.travelicon).fitCenter().into(icon3Btn);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Destroying...");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopping...");
        super.onStop();
    }

    public void onQuestionnaireOnClick(View view) {
        if(!user.isHasComeToGrace()){
            showAlertDialog(new MyAlertDialog(this,user));
        }
        else
            showAlertDialogFragment(QuestionnaireDialogFragment.newInstance(user));
    }
    public void onEquipmentOnClick(View view){
        showAlertDialogFragment(EquipmentInformationDialogFragment.newInstance(user));
    }
    public void onTravelOnClick(View view){
        showAlertDialogFragment(TravelInformationDialogFragment.newInstance(user));
    }
    public void onSouvenirOnClick(View view){
        showAlertDialogFragment(SouvenirDialogFragment.newInstance(user));
    }
    public void showAlertDialog(AlertDialog alertDialog){
        alertDialog.show();
    }
    public void showAlertDialogFragment(DialogFragment dialogFragment) {
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMoneyUpdated(int money) {
        moneyTxt.setText(String.valueOf(user.getMoney()) + "$");
    }

    @Override
    public void onGraceUpdated(int grace) {
        graceTxt.setText(String.valueOf(user.getGraceAmount()) + getString(R.string.unit));
    }
}
