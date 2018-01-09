package com.ood.clean.waterball.gracehotel.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;


public abstract class BaseDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  onBuilding(new AlertDialog.Builder(getActivity()).setView(createView())).create();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        return dialog;
    }

    protected AlertDialog.Builder onBuilding(AlertDialog.Builder builder){
        return builder;
    }

    protected abstract View createView();
}
