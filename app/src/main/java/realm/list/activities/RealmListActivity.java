package realm.list.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nimgade.pk.tutorial101.R;

import java.util.ArrayList;

import realm.list.activities.one.RealmOneActivity;
import realm.list.activities.two.RealmTwoActivity;
import support.my.classes.MyListItem;

public class RealmListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> myListItems;
    private ArrayAdapter<MyListItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.RealmListActivity_listView);
        myListItems = new ArrayList<>();
        myListItems.add(new MyListItem("Realm One Test", RealmOneActivity.class));
        myListItems.add(new MyListItem("Realm Two Test", RealmTwoActivity.class));

        adapter = new ArrayAdapter<MyListItem>(getApplicationContext(), R.layout.simple_list_item_1, myListItems);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MyListItem myListItem = (MyListItem) listView.getItemAtPosition(position);
                Intent my_Intent = new Intent(getApplicationContext(), myListItem.getActivity_Class());
                startActivity(my_Intent);
            }
        });
    }

}
