package realm.list.activities.support.realm.database;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Pankaj Nimgade on 12-05-2016.
 */
public class MyRealmHandler {

    private static final String TAG = MyRealmHandler.class.getSimpleName();

    private Context context;

    private Realm default_Realm;

    private Realm models_Realm;

    public MyRealmHandler(Context context) {
        Log.d(TAG, "MyRealmHandler: Constructor: ");
        this.context = context;
    }

    /**
     * This returns the default Realm Database, with file name <code>default.realm</code> would be created<br>
     * when first time this method is called.
     */
    public Realm getDefaultRealmDataBase() {
        default_Realm = Realm.getInstance(this.context.getApplicationContext());
        return default_Realm;
    }

    public Realm getModelsRealmDatabase() {
        Log.d(TAG, "getModelsRealmDatabase: ");
        models_Realm = Realm.getInstance(
                new RealmConfiguration.Builder(this.context)
                        .name("models.realm")
                        .build());
        return models_Realm;
    }
}
