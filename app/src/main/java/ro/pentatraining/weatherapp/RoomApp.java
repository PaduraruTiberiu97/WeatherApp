package ro.pentatraining.weatherapp;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import ro.pentatraining.weatherapp.database.AppDatabase;

public class RoomApp extends Application {
    private static WeakReference<RoomApp> appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = new WeakReference<>(this);
        AppDatabase.getInstance();
    }

    public static Context getAppContext() {
        return appContext != null ? appContext.get() : null;
    }
}
