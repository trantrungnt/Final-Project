package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import techkids.mad3.finalproject.R;

/**
 * Created by TrungNT on 5/14/2016.
 */
public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TOOLBAR_TITLE = "Category";

    private Toolbar toolbar;

    private Button btnSummation;
    private Intent intentOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initToolbar();
        initComponent();
    }

    private void initComponent() {
        btnSummation = (Button) this.findViewById(R.id.btnSummation);
        btnSummation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnSummation) {
            intentOpen = new Intent(CategoryActivity.this, AddEasyActivity.class);
            startActivity(intentOpen);
        }
    }

    //    Init toolbar, copy for all activities
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(TOOLBAR_TITLE);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //    Copy above code for all activities
}


