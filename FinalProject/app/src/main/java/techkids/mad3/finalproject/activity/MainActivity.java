package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import techkids.mad3.finalproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBeginFirstScreen, btnTest, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        btnBeginFirstScreen = (Button) this.findViewById(R.id.btnBeginFirstScreen);
        btnBeginFirstScreen.setOnClickListener(this);

        btnTest = (Button) this.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);

        btnExit = (Button) this.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBeginFirstScreen:
                openCategoryActivity();
                break;
        }
    }

    private void openCategoryActivity()
    {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
