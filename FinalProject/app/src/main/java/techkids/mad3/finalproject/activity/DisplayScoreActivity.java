package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;
import techkids.mad3.finalproject.models.SoundAccess;

public class DisplayScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView finalScoreTextView;
    private Button tryAgainButton;
    private SoundAccess soundAccess;
    private long backPressedTime = 0;

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
        soundAccess = new SoundAccess();
        loadSoundEffect();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    private void loadSoundEffect() {
        Bundle bundle = getIntent().getExtras();
        int finalScore = bundle.getInt(Helper.FINAL_SCORE_KEY);
        int totalQuestions = bundle.getInt(Helper.TOTAL_QUESTIONS);
        finalScoreTextView.setText(finalScore + "/" + totalQuestions);

        waitDisplayQuestion();

        if (finalScore<5)
            soundAccess.playSoundEffectBackground(getBaseContext(), "sound/dongvien1.mp3");
        else
            if (finalScore>=5 && finalScore<=7)
                soundAccess.playSoundEffectBackground(getBaseContext(), "sound/dongvien2.mp3");
            else
                soundAccess.playSoundEffectBackground(getBaseContext(), "sound/chucmung1.mp3");
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
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        DisplayScoreActivity.this.finish();
    }

    private void waitDisplayQuestion()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed()
    {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, Helper.EXIT_BTN_BACK_PRESS,
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}
