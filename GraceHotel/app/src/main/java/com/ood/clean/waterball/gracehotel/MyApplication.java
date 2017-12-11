package com.ood.clean.waterball.gracehotel;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.google.gson.GsonBuilder;
import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRepository;
import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRetrofitRepository;
import com.ood.clean.waterball.gracehotel.Model.Secret;
import com.ood.clean.waterball.gracehotel.Model.UserLocalRepository;
import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Threading.AndroidThreadExecutor;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.utils.DateDeserializer;

import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application{
    private static Context context;
    private static Resources resources;
    private static ThreadExecutor threadExecutor;
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        resources = getResources();
    }

    public static Context getDefaultContext() {
        return context;
    }

    public static Resources getMyResources(){
        return resources;
    }

    public static Retrofit getRetrofit(){
        if (retrofit == null)
        {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            retrofit = new Retrofit.Builder()
                    .baseUrl(Secret.SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
        }

        return retrofit;
    }

    public static QuestionnaireRepository getQuestionnaireRepository(){
        return new QuestionnaireRetrofitRepository(getRetrofit());
    }

    public static String getLanguage(){
        String display = Locale.getDefault().getDisplayLanguage();
        if (display.equals("中文"))
            return "CH";
        return "EN";
    }

    public static ThreadExecutor getThreadExecutor(){
        return threadExecutor == null ? new AndroidThreadExecutor() : threadExecutor;
    }

    public static UserRepository getUserRepository(){
        return new UserLocalRepository(getDefaultContext());
    }
}
