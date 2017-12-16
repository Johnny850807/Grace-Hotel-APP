package com.ood.clean.waterball.gracehotel.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.MyApplication;
import com.ood.clean.waterball.gracehotel.Presenter.MainPresenter;
import com.ood.clean.waterball.gracehotel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{
    private final static String TAG = "MainActivity";
    private MainPresenter presenter;
    private TextLoadingDecorator textLoadingDecorator;
    @BindView(R.id.loadingText) TextView loadingTxt;
    @BindView(R.id.loadingBar) ProgressBar loadingBar;
    @BindView(R.id.roomNumbersSpin) Spinner roomNumberSpn;
    @BindView(R.id.checkInBtn) Button checkInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(MyApplication.getThreadExecutor(), MyApplication.getUserRepository());
        presenter.setMainView(this);
        ButterKnife.bind(this);
        textLoadingDecorator = new TextLoadingDecorator(loadingTxt, 7);
        checkUserExists();
    }

    private void checkUserExists() {
        if (presenter.hasLocalUser())
        {
            disableViews(checkInBtn, roomNumberSpn);
            presenter.loadLocalUser();
            textLoadingDecorator.setText(getString(R.string.checkRoomExists));
            new Thread(textLoadingDecorator).start();
        }
    }


    public void checkInOnClick(View view) {
        if (roomNumberSpn.getSelectedItemPosition() == 0)
            Toast.makeText(this, getString(R.string.pleaseChooseRoomNumbers), Toast.LENGTH_SHORT).show();
        else
        {
            String roomNumber = (String) roomNumberSpn.getSelectedItem();
            Log.d(TAG, "Room number:" +  roomNumber + ", checking in.");
            if (presenter.hasLocalUser())
                presenter.loadLocalUser();
            else
                presenter.signIn(roomNumber);
            loadingBar.setVisibility(View.VISIBLE);
            textLoadingDecorator.setText(getString(R.string.loading));
            new Thread(textLoadingDecorator).start();
        }
    }

    @Override
    public void onPrototypePreparedCompleted() {
        Log.d(TAG,"Prototype prepared completed");
        textLoadingDecorator.setText(getString(R.string.imgPreparedCompleted));
    }

    @Override
    public void onGameItemArrangementCompleted() {
        Log.d(TAG,"GameItem Arrangement completed");
        textLoadingDecorator.setText(getString(R.string.gameItemArrangementCompleted));
    }

    @Override
    public void onSignInSucessfully(User user) {
        textLoadingDecorator.setRunning(false);
        Log.d(TAG,"User got " + user);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("User", user);

        startActivity(intent);
        Toast.makeText(getApplicationContext(), "登入", Toast.LENGTH_SHORT).show();
        loadingBar.setVisibility(View.INVISIBLE);
        disableViews(checkInBtn, roomNumberSpn);
        finish();
    }

    private void disableViews(View... views){
        for (View view : views)
            view.setEnabled(false);
    }
}
