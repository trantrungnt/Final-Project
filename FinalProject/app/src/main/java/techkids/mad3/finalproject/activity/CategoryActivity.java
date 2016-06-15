package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import techkids.mad3.finalproject.R;

/**
 * Created by TrungNT on 5/14/2016.
 */
public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSummation;
    private Intent intentOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initComponent();
    }

    private void initComponent()
    {
        btnSummation = (Button) this.findViewById(R.id.btnSummation);
        btnSummation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id==R.id.btnSummation) {
            intentOpen = new Intent(CategoryActivity.this, AddEasyActivity.class);
            startActivity(intentOpen);
        }
    }
}


