package example101.list.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nimgade.pk.tutorial101.R;

import java.util.ArrayList;

import support.my.classes.MyListItem;

public class Example101Activity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> myListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example101);
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
        listView = (ListView) findViewById(R.id.Example101Activity_listView);
        myListItems = new ArrayList<>();

        myListItems.add(new MyListItem("Industry Example", IndustryActivity.class));


        ArrayAdapter<MyListItem> adapter =
                new ArrayAdapter<MyListItem>(getApplicationContext(), R.layout.simple_list_item_1, myListItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyListItem myListItem = (MyListItem) listView.getItemAtPosition(position);
                Intent my_Intent = new Intent(getApplicationContext(), myListItem.getActivity_Class());
                startActivity(my_Intent);
            }
        });
    }

}
