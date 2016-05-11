package data.binding.list.activities;

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

import data.binding.list.activities.four.imageview.data.binding.DataBindingFourActivity;
import data.binding.list.activities.one.DataBindingOneActivity;
import data.binding.list.activities.three.DataBindingThreeActivity;
import data.binding.list.activities.two.DataBindingTwoActivity;
import support.my.classes.MyListItem;

public class DataBindingListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> myListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.DataBindingListActivity_listView);
        myListItems = new ArrayList<>();

        myListItems.add(new MyListItem("Test One Data Binding", DataBindingOneActivity.class));
        myListItems.add(new MyListItem("Test Two Data Binding", DataBindingTwoActivity.class));
        myListItems.add(new MyListItem("Test Three Data Binding", DataBindingThreeActivity.class));
        myListItems.add(new MyListItem("Test Four Data Binding", DataBindingFourActivity.class));

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
