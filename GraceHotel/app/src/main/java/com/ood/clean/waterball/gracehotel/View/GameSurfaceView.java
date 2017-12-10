package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;

import java.util.List;


public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, GameView, GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector;

    public GameSurfaceView(Context context) {
        super(context);
        setFocusable(true);
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onGameStatusUpdated(Sprite background, List<Sprite> sprites) {

    }

    @Override
    public void onShowQuestionnaire(List<QuestionModel> questionModels) {

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
