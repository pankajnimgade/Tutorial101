package realm.list.activities.three;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nimgade.pk.tutorial101.BuildConfig;
import com.nimgade.pk.tutorial101.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmThreeActivity extends AppCompatActivity {

    private static final String TAG = RealmThreeActivity.class.getSimpleName();

    private Button create_Button;
    private TextView size_TextView;
    private TextView version_name_TextView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        create_Button = (Button) findViewById(R.id.RealmThreeActivity_create_button);
        size_TextView = (TextView) findViewById(R.id.RealmThreeActivity_size_textView);
        version_name_TextView = (TextView) findViewById(R.id.RealmThreeActivity_version_name_textView);
        version_name_TextView.setText("Version Name: " + BuildConfig.VERSION_NAME);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext())
                .name("RealmThree.realm")
                .build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);

        create_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                MyDog dog = realm.createObject(MyDog.class);
                dog.setAge("SomeName");
                dog.setAge("1");
                realm.commitTransaction();
            }
        });


        final RealmResults<MyDog> dogRealmResults = realm.where(MyDog.class).findAll();

        dogRealmResults.addChangeListener(new RealmChangeListener<RealmResults<MyDog>>() {

            @Override
            public void onChange(RealmResults<MyDog> element) {
                Log.d(TAG, "onChange: " + element.size());
                size_TextView.setText("Size: " + element.size());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        realm.removeAllChangeListeners();
        realm.close();
        realm = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
