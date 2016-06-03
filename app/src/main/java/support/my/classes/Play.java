package support.my.classes;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pankaj Nimgade on 23-05-2016.
 */
public class Play {

    /**
     * volatile keyword ensures that multiple threads handle the uniqueInstance
     * variable correctly when it is being initialized to Singleton instance
     */
    private volatile static Play play;

    private static final String XML_FILE = "play_xml_file.xml";
    private static final String KEY_DATA = "SOME_DATA_KEY";
    private static final String KEY_USERNAME = "SOME_USERNAME_KEY";
    private static final String KEY_PASSWORD = "SOME_PASSWORD_KEY";

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    private Play() {
    }

    public static Play getInstance(Context context) {
        if (play == null) {
            synchronized (Play.class) {
                if (play == null) {
                    sharedPreferences = context.getSharedPreferences(XML_FILE, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    play = new Play();
                }
            }
        }
        return play;
    }

    public boolean saveSomeData(String someData) {
        editor.putString(KEY_DATA, someData);
        return editor.commit();
    }

    public String readSomeData() {
        return sharedPreferences.getString(KEY_DATA, "default Value");
    }

    public boolean saveUserNameData(String username) {
        editor.putString(KEY_USERNAME, username);
        return editor.commit();
    }

    public String readUserNameData() {
        return sharedPreferences.getString(KEY_USERNAME, "default username Value");
    }

    public boolean savePasswordData(String password) {
        editor.putString(KEY_PASSWORD, password);
        return editor.commit();
    }

    public String readPasswordData() {
        return sharedPreferences.getString(KEY_PASSWORD, "default password value");
    }
}
