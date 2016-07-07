package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;
import techkids.mad3.finalproject.models.SoundAccess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBeginFirstScreen, btnTest, btnExit;
    private Intent intentOpen;
    private SoundAccess soundAccess;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        playSoundMain();
    }

    private void initComponents() {
        btnBeginFirstScreen = (Button) this.findViewById(R.id.btnBeginFirstScreen);
        btnBeginFirstScreen.setOnClickListener(this);

        btnTest = (Button) this.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);

        btnExit = (Button) this.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);

        soundAccess = new SoundAccess();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBeginFirstScreen:
                openCategoryActivity();
                stopSoundMain();
                break;
            case R.id.btnExit:
                closeMainActivity();
                stopSoundMain();
                break;
        }
    }

    private void closeMainActivity() {
        this.finish();
    }

    private void openCategoryActivity()
    {
        intentOpen = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intentOpen);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void playSoundMain()
    {
        soundAccess.loadSoundBackground(getApplicationContext(), Helper.MUSIC_SOUND_MAIN_BACKGROUND);
    }

    private void stopSoundMain()
    {
        soundAccess.stopSoundBackground();
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
            soundAccess.stopSoundBackground();
            super.onBackPressed();
        }
    }
}
