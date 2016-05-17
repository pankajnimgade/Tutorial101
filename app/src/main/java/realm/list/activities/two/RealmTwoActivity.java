package realm.list.activities.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nimgade.pk.tutorial101.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import support.my.classes.StartUp;

public class RealmTwoActivity extends AppCompatActivity {

    private static final String TAG = RealmTwoActivity.class.getSimpleName();

    private TextView textView;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        textView = (TextView) findViewById(R.id.RealmTwoActivity_data_textView);

        realm = Realm.getInstance(
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("RealmTwo.realm")
                        .build());
    }

    public void startRealm(View view) {
        Toast.makeText(RealmTwoActivity.this, "startRealm", Toast.LENGTH_SHORT).show();
        // Use them like regular java objects
        final Dog dog = new Dog();
        dog.setName("Rex");
        dog.setAge(1);
        Log.v(TAG, "Name of the dog: " + dog.getName());


        // Persist your data easily
        realm.beginTransaction();
        realm.copyToRealm(dog);
        realm.commitTransaction();
        Log.v(TAG, "written the dog to realm database " + dog.getName());

    }

    public void readRealm(View view) {
        Toast.makeText(RealmTwoActivity.this, "ReadRealm", Toast.LENGTH_SHORT).show();

        Dog theDog = realm.where(Dog.class).equalTo("name", "Rex").findFirst();
        if (theDog != null) {
            String jsonResult = (StartUp.getGson().create()).toJson(theDog);
            textView.setText("" + jsonResult);
        } else {
            textView.setText("it was null");

        }

    }
}
