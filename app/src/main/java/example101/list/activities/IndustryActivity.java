package example101.list.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.nimgade.pk.tutorial101.R;

import org.apache.commons.io.IOUtils;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class IndustryActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = IndustryActivity.class.getSimpleName();
    private ProgressDialog progressDialog;

    private CheckBox checkBox_3;
    private CheckBox checkBox_2;
    private ListView listView;
    private ArrayList<MySkill> mySkills_List;
    private ArrayAdapter<MySkill> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        mySkills_List = new ArrayList<>();

        checkBox_3 = (CheckBox) findViewById(R.id.IndustryActivity_3_checkBox);
        checkBox_2 = (CheckBox) findViewById(R.id.IndustryActivity_2_checkBox);
        checkBox_3.setOnCheckedChangeListener(this);
        checkBox_2.setOnCheckedChangeListener(this);
        listView = (ListView) findViewById(R.id.IndustryActivity_listView);
    }

    private void addSkillToListView(ArrayList<MySkill> mySkills) {
        if (mySkills != null) {
            this.mySkills_List.addAll(mySkills);
        }

        if (!checkBox_3.isChecked()) {
            Iterator<MySkill> iterator = this.mySkills_List.iterator();
            while (iterator.hasNext()) {
                MySkill mySkill = iterator.next();
                if (mySkill.getCategory_id().contentEquals("3")) {
                    iterator.remove();
                }
            }
        }

        if (!checkBox_2.isChecked()) {
            Iterator<MySkill> iterator = this.mySkills_List.iterator();
            while (iterator.hasNext()) {
                MySkill mySkill = iterator.next();
                if (mySkill.getCategory_id().contentEquals("2")) {
                    iterator.remove();
                }
            }
        }

        IndustryActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new ArrayAdapter<MySkill>(getApplicationContext(), R.layout.simple_list_item_1, mySkills_List);
                listView.setAdapter(adapter);
                listView.invalidate();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.IndustryActivity_3_checkBox:
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: IndustryActivity_1_checkBox: " + isChecked);
                    loadForId(3);
                } else {
                    Log.d(TAG, "onCheckedChanged: IndustryActivity_1_checkBox: " + isChecked);
                    addSkillToListView(null);
                }
                break;
            case R.id.IndustryActivity_2_checkBox:
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: IndustryActivity_2_checkBox: " + isChecked);
                    loadForId(2);
                } else {
                    Log.d(TAG, "onCheckedChanged: IndustryActivity_2_checkBox: " + isChecked);
                    addSkillToListView(null);
                }
                break;
        }
    }

    private void loadForId(int id) {
        new MyTask().execute(id);
    }

    private class MyTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Integer... params) {
            int id = params[0];
            try {
                String url = "http://hireme365.com/appapi/getskillbycat?id=" + id;
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.connect();
                String result = IOUtils.toString(httpURLConnection.getInputStream());
                Log.d(TAG, "doInBackground: result\n" + result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s == null)
                return;
            Type type = new com.google.gson.reflect.TypeToken<ArrayList<MySkill>>() {
            }.getType();
            ArrayList<MySkill> mySkills = (new Gson()).fromJson(s, type);
            for (MySkill mySkill : mySkills) {
                Log.d(TAG, "onPostExecute: mySkill.getName(): " + mySkill.getName());
            }
            addSkillToListView(mySkills);
        }
    }


    private class MySkill {
        private String id;
        private String category_id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
