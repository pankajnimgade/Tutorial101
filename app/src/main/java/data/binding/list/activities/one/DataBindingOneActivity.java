package data.binding.list.activities.one;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nimgade.pk.tutorial101.R;
import com.nimgade.pk.tutorial101.databinding.ActivityDataBindingOneBinding;

public class DataBindingOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingOneBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_one);
        User user = new User("Mac", "Zinner");
        binding.setUser(user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

    }
}
