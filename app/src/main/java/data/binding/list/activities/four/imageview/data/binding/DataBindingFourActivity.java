package data.binding.list.activities.four.imageview.data.binding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nimgade.pk.tutorial101.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;


public class DataBindingFourActivity extends AppCompatActivity {

    private static final String TAG = "DataBindingFour";

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<MyUser> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_four);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        recyclerView = (RecyclerView) findViewById(R.id.DataBindingFourActivity_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadListFromAPI();
    }

    private void loadListFromAPI() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://api.chatndate.com/web/api/users")
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.d(TAG, "onFailure: ");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String result = response.body().string();
                    Log.d(TAG, "onResponse: " + result);
                    users = (new Gson()).fromJson(result, new TypeToken<ArrayList<MyUser>>(){}.getType());
                    userAdapter = new UserAdapter(getApplicationContext(), users);

                    for (MyUser user:users) {
                        Log.d(TAG, "onResponse:getEmail: "+user.getEmail());
                    }

                    DataBindingFourActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setAdapter(userAdapter);
                            userAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
