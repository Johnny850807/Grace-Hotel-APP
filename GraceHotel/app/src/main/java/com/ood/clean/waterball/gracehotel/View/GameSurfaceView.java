package com.ood.clean.waterball.gracehotel.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.MyApplication;
import com.ood.clean.waterball.gracehotel.Presenter.GamePresenter;
import com.ood.clean.waterball.gracehotel.R;

import java.util.List;


public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, GameView, GestureDetector.OnGestureListener {
    private static final String TAG = "GameSurfaceView";
    private User user;
    private Background background;
    private GamePresenter gamePresenter;
    private GestureDetector gestureDetector;
    private GameParentView gameParentView;

    public GameSurfaceView(Context context, User user, GameParentView gameParentView) {
        super(context);
        this.user = user;
        this.gameParentView = gameParentView;
        setFocusable(true);
        getHolder().addCallback(this);
        gestureDetector = new GestureDetector(getContext(), this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG, "Touch, " + event.getAction());
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            gamePresenter.touchScreen((int)event.getX(), (int)event.getY());
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "Surface creating.");
        gamePresenter = new GamePresenter(user, MyApplication.getUserRepository(), MyApplication.getThreadExecutor());
        gamePresenter.setGameView(this);
        gamePresenter.gameStart();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "Surface destroying.");
        gamePresenter.setRunning(false);
        while (true) {
            try {
                gamePresenter.getGameCycleThread().join();  // clean join
                break;
            } catch (InterruptedException e) {
                Log.d(TAG, "game cycle thread interrupted during surface destroyed.");
            }
        }
    }

    @SuppressLint("WrongCall")
    @Override
    public void onGameStatusUpdated(Background background) {
        this.background = background;
        try{
            Canvas canvas = getHolder().lockCanvas();
            onDraw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }catch (IllegalArgumentException err){
            //err.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        background.draw(canvas);
    }

    @Override
    public void onShowQuestionnaire(List<QuestionModel> questionModels) {

    }

    @Override
    public void onNoGameItemsFound() {
        Toast.makeText(getContext(), R.string.roomHasNoSprites, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMoneyEarned(Sprite sprite, int money) {
        gameParentView.onMoneyEarned(sprite, money);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float dx, float dy) {
        Log.d(TAG, "Scrolling... dx: " + dx + ", dy: " + dy );
        gamePresenter.scrollBackground((int)dx, (int)dy);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "Long pressing");
    }

    @Override
    public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent1, final float vX, final float vY) {
        Log.d(TAG, "Flinging... vX: " + vX + ", vY" + vY);

        new Thread(){
            @Override
            public void run() {
                int deltaX = (int)vX / 100;
                int deltaY = (int)vY / 100;
                int diffX = (int)(motionEvent.getX() - motionEvent1.getX());
                int diffY = (int)(motionEvent.getY() - motionEvent1.getY());
                int factorX = diffX > 0 ? 1 : -1;
                int factorY = diffY > 0 ? 1 : -1;
                while(Math.abs(deltaX) < 5 || Math.abs(deltaY) < 5)
                {
                    gamePresenter.scrollBackground(deltaX, deltaY);
                    deltaX = deltaX - (5*factorX) > 0 ? deltaX - 5 : 0;
                    deltaY = deltaY - 5 > 0 ? deltaY - 5 : 0;
                }
            }
        };

        return false;
    }
}
