package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;

public class DisplayScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView finalScoreTextView;
    private Button tryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);

        initComponents();
    }

    private void initComponents() {
        finalScoreTextView = (TextView) findViewById(R.id.finalScore);
        tryAgainButton = (Button) findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        int finalScore = bundle.getInt(Helper.FINAL_SCORE_KEY);
        int totalQuestions = bundle.getInt(Helper.TOTAL_QUESTIONS);
        finalScoreTextView.setText(finalScore + "/" + totalQuestions);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tryAgainButton:
                moveToCategoryActivity();
                break;
        }
    }

    private void moveToCategoryActivity() {
        Intent intent = new Intent(DisplayScoreActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
}
