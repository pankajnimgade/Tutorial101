package data.binding.list.activities.three;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.nimgade.pk.tutorial101.MyMovie;
import com.nimgade.pk.tutorial101.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataBindingThreeActivity extends AppCompatActivity {

    private static final String TAG = "DataBindingThree";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        downloadJSON();
    }

    private void downloadJSON() {
        try {
            run("http://api.androidhive.info/json/movies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String url) throws IOException {
        final StringBuilder result = new StringBuilder();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure: ");

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result_json = response.body().string();
                Log.d(TAG, "onResponse: " + result_json);
                Type list_type = new com.google.gson.reflect.TypeToken<ArrayList<Movie>>() {
                }.getType();
                ArrayList<Movie> myMovies = (new Gson()).fromJson(result_json, list_type);
                for (Movie myMovie:myMovies) {
                    Log.d(TAG, "onResponse: "+myMovie.getTitle());
                }
            }
        });
    }

    private void passResult(String result_json) {
        Log.d(TAG, "passResult: " + result_json);
    }

}
