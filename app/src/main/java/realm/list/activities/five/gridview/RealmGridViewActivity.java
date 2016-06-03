package realm.list.activities.five.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.nimgade.pk.tutorial101.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmGridViewActivity extends AppCompatActivity {

    private static final String TAG = RealmGridViewActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MyCityAdapter myCityAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_grid_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        recyclerView = (RecyclerView) findViewById(R.id.RealmGridViewActivity_grid_view);

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("RealmGridView.realm")
                        .build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);

        if (myCityAdapter == null) {
            List<City> cities = loadCities();
            ArrayList<MyCity> myCities = new ArrayList<>();
            for (City city : cities) {
                myCities.add(new MyCity(city.getName(), city.getVotes()));
                Log.d(TAG, "initializeUI: CityName: " + city.getName() + " Vote: " + city.getVotes());
            }
            myCityAdapter = new MyCityAdapter(getApplicationContext(), myCities);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(myCityAdapter);
            myCityAdapter.notifyDataSetChanged();
            recyclerView.invalidate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private List<City> loadCities() {
        // In this case we're loading from local assets.
        // NOTE: could alternatively easily load from network
        InputStream stream;
        try {
            stream = getAssets().open("cities.json");
        } catch (IOException e) {
            return null;
        }

        Gson gson = new GsonBuilder().create();

        JsonElement json = new JsonParser().parse(new InputStreamReader(stream));
//        String json = null;
//        try {
//            json = IOUtils.toString(stream);
//            Log.d(TAG, "loadCities: " + json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (json == null) {
//                return null;
//            }
//        }

        ArrayList<City> cities = gson.fromJson(json, new com.google.gson.reflect.TypeToken<ArrayList<City>>() {
        }.getType());

        for (City city : cities) {
            Log.d(TAG, "loadCities: CityName: " + city.getName() + " Vote: " + city.getVotes());
        }

        // Open a transaction to store items into the realm
        // Use copyToRealm() to convert the objects into proper RealmObjects managed by Realm.
        realm.beginTransaction();
        Collection<City> realmCities = realm.copyToRealm(cities);
        realm.commitTransaction();

        return cities;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
