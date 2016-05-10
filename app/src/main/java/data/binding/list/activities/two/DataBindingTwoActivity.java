package data.binding.list.activities.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.nimgade.pk.tutorial101.R;

import java.util.ArrayList;

public class DataBindingTwoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        recyclerView = (RecyclerView) findViewById(R.id.DataBindingTwoActivity_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        books = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            books.add(new Book("Title_" + i, "Author_" + i));
        }

        BooksRecyclerAdapter adapter = new BooksRecyclerAdapter(getApplicationContext(), books);
        recyclerView.setAdapter(adapter);
    }
}
