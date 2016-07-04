package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.models.SoundAccess;

/**
 * Created by TrungNT on 5/14/2016.
 */
public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSummation, btnSubtract;
    private Intent intentOpen;
    private SoundAccess soundAccess;

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
        }


    }

    private void openSubstractEasyActivity() {
        intentOpen = new Intent(CategoryActivity.this, SubstractEasyActivity.class);
        startActivity(intentOpen);
    }

    private void openAddEasyActivity()
    {
        intentOpen = new Intent(CategoryActivity.this, AddEasyActivity.class);
        startActivity(intentOpen);
    }

    private void loadSoundCategory()
    {
        soundAccess.loadSoundBackground(getApplicationContext(), "sound/music_category.mp3");
    }

    private void stopSoundCategory()
    {
        soundAccess.stopSoundBackground();
    }
}


