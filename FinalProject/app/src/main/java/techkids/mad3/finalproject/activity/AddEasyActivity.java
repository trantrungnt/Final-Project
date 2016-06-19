package techkids.mad3.finalproject.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.fragments.AddEasyFragment;

public class AddEasyActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TOOLBAR_TITLE = "Easy";
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 10;

    private Toolbar toolbar;
    private Random random = new Random();

    private Button submitButton, answerButtonA, answerButtonB, answerButtonC, answerButtonD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        initToolbar();
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        generateNewQuestion();
    }

    private void initComponents() {
        submitButton = (Button) findViewById(R.id.submitButton);
        answerButtonA = (Button) findViewById(R.id.answerButtonA);
        answerButtonB = (Button) findViewById(R.id.answerButtonB);
        answerButtonC = (Button) findViewById(R.id.answerButtonC);
        answerButtonD = (Button) findViewById(R.id.answerButtonD);

        submitButton.setOnClickListener(this);
        answerButtonA.setOnClickListener(this);
        answerButtonB.setOnClickListener(this);
        answerButtonC.setOnClickListener(this);
        answerButtonD.setOnClickListener(this);
    }

    private int random(int low, int high) {
        return low + random.nextInt(high);
    }

    private void generateNewQuestion() {
        if (null != findViewById(R.id.questionFragment)) {
            int sum = random(1 + MIN_VALUE, MAX_VALUE);
            int firstNumber = random(MIN_VALUE, sum - 1);
            int secondNumber = sum - firstNumber;
            Fragment addEasyFragment = new AddEasyFragment(firstNumber, secondNumber);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.questionFragment, addEasyFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {

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
                Intent intent = new Intent(AddEasyActivity.this, CategoryActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //    Copy above code for all activities
}
