package support.my.classes;

import android.app.Application;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;

/**
 * Created by Pankaj Nimgade on 08-05-2016.
 */
public class StartUp extends Application {

    private static final String TAG = StartUp.class.getSimpleName();

    private static GsonBuilder gson;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: App has started");
    }

    public static GsonBuilder getGson() {
        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        return gson;
    }
}
