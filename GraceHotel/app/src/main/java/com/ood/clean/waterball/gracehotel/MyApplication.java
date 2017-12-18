package com.ood.clean.waterball.gracehotel;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRepository;
import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRetrofitRepository;
import com.ood.clean.waterball.gracehotel.Model.Secret;
import com.ood.clean.waterball.gracehotel.Model.Serializer;
import com.ood.clean.waterball.gracehotel.Model.UserLocalRepository;
import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.domain.ItemArranger;
import com.ood.clean.waterball.gracehotel.Model.domain.NoPainNoGain;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.BaseSpriteProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.MoneyProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.TreasureProxy;
import com.ood.clean.waterball.gracehotel.Threading.AndroidThreadExecutor;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.utils.DateDeserializer;
import com.ood.clean.waterball.gracehotel.utils.RuntimeTypeAdapterFactory;

import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application{
    private static Context context;
    private static Resources resources;
    private static ThreadExecutor threadExecutor;
    private static UserRepository userRepository;
    private static QuestionnaireRepository questionnaireRepository;
    private static Retrofit retrofit;
    private static ItemArranger itemArranger;
    private static Gson deGson;
    private static Gson seGson = new Gson();
    private static Serializer serializer = new Serializer() {
        @Override
        public String serialize(Object object) {
            return getSeGson().toJson(object);
        }
        @Override
        public <T> T deserialize(String data, Class<T> clazz) {
            return getDeGson().fromJson(data, clazz);
        }
    };

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
        return questionnaireRepository == null ? questionnaireRepository = new QuestionnaireRetrofitRepository(getRetrofit())
                : questionnaireRepository;
    }

    public static String getLanguage(){
        String display = Locale.getDefault().getDisplayLanguage();
        if (display.equals("中文"))
            return "CH";
        return "EN";
    }

    public static ThreadExecutor getThreadExecutor(){
        return threadExecutor == null ? threadExecutor = new AndroidThreadExecutor() : threadExecutor;
    }

    public static UserRepository getUserRepository(){
        return userRepository == null ? userRepository = new UserLocalRepository(getDefaultContext(),
                getSerializer(), getItemArranger())
                : userRepository;
    }

    public static ItemArranger getItemArranger() {
        return itemArranger == null ? itemArranger = new NoPainNoGain() : itemArranger;
    }

    // used for deserialization
    public static Gson getDeGson() {
        if (deGson == null)
        {
            RuntimeTypeAdapterFactory<BaseSpriteProxy> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                    .of(BaseSpriteProxy.class, "spriteName")
                    .registerSubtype(MoneyProxy.class, SpriteName.MONEY.toString())
                    .registerSubtype(TreasureProxy.class, SpriteName.TREASURE.toString());
            deGson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        }
        return deGson;
    }

    // used for serialization
    public static Gson getSeGson() {
        return seGson;
    }

    public static Serializer getSerializer() {
        return serializer;
    }
}
