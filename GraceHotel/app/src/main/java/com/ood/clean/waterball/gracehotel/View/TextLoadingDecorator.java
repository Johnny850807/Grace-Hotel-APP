package com.ood.clean.waterball.gracehotel.View;


import android.os.Handler;
import android.widget.TextView;

public class TextLoadingDecorator implements Runnable{
    private Handler handler = new Handler();
    private TextView textView;
    private String nowText = "";
    private boolean running;
    private final int dotCount;
    private int cycle = 0;  // 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3,.. repeatedly

    public TextLoadingDecorator(TextView textView, int dotCount){
        this.textView = textView;
        this.dotCount = dotCount;
    }

    public void run(){
        running = true;
        while(running)
            try {
                String txt = nowText + getDotStr(cycle);
                emitText(txt);
                cycle = cycle + 1 >= dotCount ? 0 : cycle + 1;
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void emitText(final String text){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }

    private String getDotStr(int count){
        StringBuilder strb = new StringBuilder();
        for (int i = 0 ; i < count ; i ++)
            strb.append(".");
        return strb.toString();
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public void setText(String text){
        nowText = text;
    }
}
