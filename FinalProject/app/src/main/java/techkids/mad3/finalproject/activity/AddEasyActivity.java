package techkids.mad3.finalproject.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.fragments.AddEasyFragment;

public class AddEasyActivity extends AppCompatActivity {
    private final String TOOLBAR_TITLE = "Easy";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_easy);
        initToolbar();
        initFirstFragment();
    }

    private void initFirstFragment() {
        if (null != findViewById(R.id.addEasyActivity)) {
            Fragment addEasyFragment = new AddEasyFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.addEasyActivity, addEasyFragment);
            fragmentTransaction.commit();
        }
    }

    //    Init toolbar, copy for all activities
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(TOOLBAR_TITLE);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddEasyActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//    Copy above code for all activities
}
