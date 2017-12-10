package com.ood.clean.waterball.gracehotel.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    private final static String TAG = "GameActivity";
    private User user;
    @BindView(R.id.icon1) ImageButton icon1Btn;
    @BindView(R.id.icon2) ImageButton icon2Btn;
    @BindView(R.id.icon3) ImageButton icon3Btn;
    @BindView(R.id.icon4) ImageButton icon4Btn;
    @BindView(R.id.surveyBtn) ImageButton surveyBtn;
    @BindView(R.id.container) RelativeLayout container;
    @BindView(R.id.roomNumberTxt) TextView roomNumberTxt;
    @BindView(R.id.moneyTxt) TextView moneyTxt;

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

    private void setupViews(){
        initAndAddGameSurfaceView();
        setupIconButtons();
        roomNumberTxt.setText(user.getRoomNumber());
        moneyTxt.setText(String.valueOf(user.getMoney()));
    }

    private void initAndAddGameSurfaceView(){
        GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
        gameSurfaceView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(gameSurfaceView);
    }

    private void setupIconButtons(){

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

    public void onQuesitonnaireOnClick(View view) {

    }
}
