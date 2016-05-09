package support.my.classes;

import android.app.Application;
import android.util.Log;

/**
 * Created by Pankaj Nimgade on 08-05-2016.
 */
public class StartUp extends Application{

    private static final String TAG = StartUp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: App has started");
    }

}
