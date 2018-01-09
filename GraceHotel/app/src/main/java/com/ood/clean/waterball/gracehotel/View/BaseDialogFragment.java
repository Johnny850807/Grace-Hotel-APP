package com.ood.clean.waterball.gracehotel.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;


public abstract class BaseDialogFragment extends DialogFragment {
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog =  onBuilding(new AlertDialog.Builder(getActivity()).setView(createView())).create();
        return dialog;
    }

    protected AlertDialog.Builder onBuilding(AlertDialog.Builder builder){
        return builder;
    }

    protected boolean shouldDisablePositiveButtonDefaultAction(){
        return true;
    }

    protected abstract View createView();
}
