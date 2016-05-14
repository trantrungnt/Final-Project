package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import techkids.mad3.finalproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TOOLBAR_TITLE = "Easy";

    private Toolbar toolbar;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initComponents();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(TOOLBAR_TITLE);
    }

    private void initComponents() {
        button = (Button) findViewById(R.id.btnBeginFirstScreen);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBeginFirstScreen:
                Intent intent = new Intent(MainActivity.this, AddEasyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
