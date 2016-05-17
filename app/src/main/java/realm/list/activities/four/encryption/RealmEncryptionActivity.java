package realm.list.activities.four.encryption;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nimgade.pk.tutorial101.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmEncryptionActivity extends AppCompatActivity {

    private static final String TAG = RealmEncryptionActivity.class.getSimpleName();

    private Button start_Button;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        start_Button = (Button) findViewById(R.id.RealmEncryptionActivity_start_button);
        start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                MyPerson myPerson = realm.createObject(MyPerson.class);
                myPerson.setName("Mike");
                myPerson.setAge(14);
                realm.commitTransaction();

                MyPerson person = realm.where(MyPerson.class).findFirst();
                Log.i(TAG, String.format("Person name: %s", person.getName()));
            }
        });

        byte[] key = Base64.encode("hello".getBytes(), Base64.DEFAULT);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("RealmEncryption.realm")
                        .encryptionKey(key)
                        .build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);

    }
}
