package techkids.mad3.finalproject.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;
import techkids.mad3.finalproject.models.SoundAccess;

/**
 * Created by TrungNT on 5/14/2016.
 */
public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSummation, btnSubtract, btnPuzzle;
    private Intent intentOpen;
    private SoundAccess soundAccess;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initComponents();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        loadSoundCategory();
    }

    private void initComponents() {
        btnSummation = (Button) this.findViewById(R.id.btnSummation);
        btnSummation.setOnClickListener(this);
        btnSubtract = (Button) this.findViewById(R.id.btnSubtraction);
        btnSubtract.setOnClickListener(this);

        soundAccess = new SoundAccess();

        btnPuzzle = (Button) this.findViewById(R.id.btnPuzzle);
        btnPuzzle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id)
        {
            case R.id.btnSummation:
                openAddEasyActivity();
                stopSoundCategory();
                break;
            case R.id.btnSubtraction:
                openSubstractEasyActivity();
                stopSoundCategory();
                break;
            case R.id.btnPuzzle:
                openPuzzleActivity();
                stopSoundCategory();
                break;
        }


    }

    private void openSubstractEasyActivity() {
        intentOpen = new Intent(CategoryActivity.this, SubstractEasyActivity.class);
        startActivity(intentOpen);
        CategoryActivity.this.finish();
    }

    private void openAddEasyActivity()
    {
        intentOpen = new Intent(CategoryActivity.this, AddEasyActivity.class);
        startActivity(intentOpen);
        CategoryActivity.this.finish();
    }

    private void openPuzzleActivity()
    {
        intentOpen = new Intent(CategoryActivity.this, PuzzleActivity.class);
        startActivity(intentOpen);
        CategoryActivity.this.finish();
    }

    private void loadSoundCategory()
    {
        soundAccess.loadSoundBackground(getApplicationContext(), Helper.MUSIC_SOUND_CATEGORY);
    }

    private void stopSoundCategory()
    {
        soundAccess.stopSoundBackground();
    }

    @Override
    public void onBackPressed()
    {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            Toast.makeText(this, Helper.EXIT_BTN_BACK_PRESS,
                    Toast.LENGTH_SHORT).show();
        } else {
            soundAccess.stopSoundBackground();
            super.onBackPressed();
        }
        backPressedTime = t;
    }
}


