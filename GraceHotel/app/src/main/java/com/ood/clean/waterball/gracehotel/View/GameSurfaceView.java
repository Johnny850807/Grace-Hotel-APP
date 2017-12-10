package com.ood.clean.waterball.gracehotel.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Presenter.GamePresenter;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, GameView, GestureDetector.OnGestureListener {
    private Sprite background;
    private List<Sprite> gameItems;
    private GamePresenter gamePresenter;
    private GestureDetector gestureDetector;

    public GameSurfaceView(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
        gestureDetector = new GestureDetector(getContext(), this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "Surface creating.");
        gamePresenter = new GamePresenter();
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
    public void onGameStatusUpdated(Sprite background, List<Sprite> sprites) {
        this.background = background;
        this.gameItems = sprites;
        Canvas canvas = getHolder().lockCanvas();
        onDraw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawSprite(canvas, background);
    }

    private void drawSprite(Canvas canvas, Sprite sprite){
        canvas.drawBitmap(sprite.nextBitmap(), sprite.getX(), sprite.getY(), null);
    }

    @Override
    public void onShowQuestionnaire(List<QuestionModel> questionModels) {
        QuestionnaireDialogFragment fragment = QuestionnaireDialogFragment.newInstance((ArrayList<QuestionModel>) questionModels);
        showAlertDialogFragment(fragment);
    }

    public void showAlertDialogFragment(DialogFragment dialogFragment) {
        dialogFragment.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "dialog");
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
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
