package realm.list.activities.one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nimgade.pk.tutorial101.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class RealmOneActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = RealmOneActivity.class.getSimpleName();

    private Button serverButton;
    private Button realmButton;
    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        serverButton = (Button) findViewById(R.id.RealmOneActivity_server_button);
        serverButton.setOnClickListener(this);
        realmButton = (Button) findViewById(R.id.RealmOneActivity_realm_button);
        realmButton.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.RealmOneActivity_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RealmOneActivity_server_button:
                loadFromServer();
                break;
            case R.id.RealmOneActivity_realm_button:
                loadFromRealm();
                break;
        }
    }

    private void loadFromRealm() {
        UserTransactions userTransactions = new UserTransactions(getApplicationContext());
        Iterator<User> userIterator = userTransactions.readUserList();
        if (userIterator != null) {
            users.clear();
            while (userIterator.hasNext()) {
                users.add(userIterator.next());
            }
            if (users != null) {
                Log.d(TAG, "loadFromRealm: users:" + users.size());
                userAdapter = new UserAdapter(getApplicationContext(), users);
                recyclerView.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();
            }

        }
    }

    private void loadFromServer() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder().url("http://api.chatndate.com/web/api/users").build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.d(TAG, "onFailure: ");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String result = response.body().string();
                    Log.d(TAG, "onResponse: \n" + result);
                    UserTransactions userTransactions = new UserTransactions(getApplicationContext());
                    userTransactions.saveUserJson(result);
                    if (result == null)
                        return;
                    if (result.contentEquals(""))
                        return;
                    users = (new Gson()).fromJson(result, new TypeToken<ArrayList<User>>() {
                    }.getType());
                    userAdapter = new UserAdapter(getApplicationContext(), users);
                    RealmOneActivity.this.runOnUiThread(new Runnable() {
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
