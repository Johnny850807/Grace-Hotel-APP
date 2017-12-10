package com.ood.clean.waterball.gracehotel.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.ood.clean.waterball.gracehotel.Model.UserLocalRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Presenter.MainPresenter;
import com.ood.clean.waterball.gracehotel.R;
import com.ood.clean.waterball.gracehotel.Threading.AndroidThreadExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{
    private final static String TAG = "MainActivity";
    private MainPresenter presenter;
    @BindView(R.id.loadingBar) ProgressBar loadingBar;
    @BindView(R.id.roomNumbersSpin) Spinner roomNumberSpn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ButterKnife.bind(this);
        checkUserExists();
    }

    private void checkUserExists() {
        if (presenter.hasUser())
        {
            presenter.getUser();
            //TODO loading UX
        }
    }

    private void init() {
        presenter = new MainPresenter(new AndroidThreadExecutor(), new UserLocalRepository(this));
        presenter.setMainView(this);
    }

    public void checkInOnClick(View view) {
        if (roomNumberSpn.getSelectedItemPosition() == 0)
            Toast.makeText(this, getString(R.string.pleaseChooseRoomNumbers), Toast.LENGTH_SHORT).show();
        else
        {
            String roomNumber = (String) roomNumberSpn.getSelectedItem();
            Log.d(TAG, "Room number:" +  roomNumber + ", checking in.");
            if (presenter.hasUser())
                presenter.getUser();
            else
                presenter.signIn(roomNumber);
            //TODO loading UX
        }
    }

    @Override
    public void onPrototypePreparedCompleted() {
        Log.d(TAG,"Prototype prepared completed");

    }

    @Override
    public void onGameItemArrangementCompleted() {
        Log.d(TAG,"GameItem Arrangement completed");

    }

    @Override
    public void onSignInSucessfully(User user) {
        Log.d(TAG,"User got " + user);
        roomNumberSpn.setEnabled(false);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("User", user);
        //startActivity(intent);
        //finish();
    }
}
