package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;
import techkids.mad3.finalproject.database.DatabaseAccess;
import techkids.mad3.finalproject.fragments.CompareFragment;
import techkids.mad3.finalproject.models.Question;
import techkids.mad3.finalproject.models.SoundAccess;

/**
 * Created by TrungNT on 7/10/2016.
 */
public class CompareActivity extends AppCompatActivity implements View.OnClickListener {
    private Button submitButton, answerButtonA, answerButtonB, answerButtonC, answerButtonD;
    private final int TOTAL_ANSWERS = 4;
    private final int TOTAL_QUESTIONS = 9;
    private SoundAccess soundAccess;
    private Button btnCheckA, btnCheckB, btnCheckC, btnCheckD;
    private int currentQuestionNumber = -1;
    private TextView questionNumber;
    private FragmentTransaction fragmentTransaction;
    private CompareFragment compareFragment;
    private ArrayList<String> answers = new ArrayList<>();
    private int score = 0;
    private int[] a;
    private long backPressedTime = 0;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        initComponents();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        generateNewQuestion();
    }

    private void initComponents() {
        questionNumber = (TextView) findViewById(R.id.questionNumber);
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

        soundAccess = new SoundAccess();
        soundAccess.initSoundEffects(getBaseContext());

        if (currentQuestionNumber < TOTAL_QUESTIONS)
            submitButton.setText(Helper.NEXT_BTN_SUBMIT);

        btnCheckA = (Button) this.findViewById(R.id.btnCheckA);
        btnCheckB = (Button) this.findViewById(R.id.btnCheckB);
        btnCheckC = (Button) this.findViewById(R.id.btnCheckC);
        btnCheckD = (Button) this.findViewById(R.id.btnCheckD);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String answer1, answer2, answer3, answer4;

        switch (id) {
            case R.id.submitButton:
                submitFunction();
                invisibleCheckAll();
                break;
            case R.id.answerButtonA:
                answer1 = answerButtonA.getText().toString().substring(3);
                compareFragment.getResultFromUser().setText(answer1);
                loadSoundEffects(answer1);
                displayCheckA(answer1);
                break;
            case R.id.answerButtonB:
                answer2 = answerButtonB.getText().toString().substring(3);
                compareFragment.getResultFromUser().setText(answer2);
                loadSoundEffects(answer2);
                displayCheckB(answer2);
                break;
            case R.id.answerButtonC:
                answer3 = answerButtonC.getText().toString().substring(3);
                compareFragment.getResultFromUser().setText(answer3);
                loadSoundEffects(answer3);
                displayCheckC(answer3);
                break;
            case R.id.answerButtonD:
                answer4 = answerButtonD.getText().toString().substring(3);
                compareFragment.getResultFromUser().setText(answer4);
                loadSoundEffects(answer4);
                displayCheckD(answer4);
                break;
        }
    }

    private void generateNewQuestion() {
        if (null != findViewById(R.id.questionFragment)) {
            currentQuestionNumber++;
            answers.clear();

            questionNumber.setText("Câu " + currentQuestionNumber);

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            questions = databaseAccess.getQuestionsCompare();
            databaseAccess.close();

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            String content = questions.get(currentQuestionNumber).getContent_question();
            compareFragment = new CompareFragment(content);
            fragmentTransaction.setCustomAnimations(R.anim.left_to_right, 0);
            fragmentTransaction.replace(R.id.questionFragment, compareFragment).commit();

            answerButtonA.setText("A. " + questions.get(currentQuestionNumber).getAnswer_a());
            answerButtonB.setText("B. " + questions.get(currentQuestionNumber).getAnswer_b());
            answerButtonC.setText("C. " + questions.get(currentQuestionNumber).getAnswer_c());
            answerButtonD.setText("D. " + questions.get(currentQuestionNumber).getAnswer_d());

            if (currentQuestionNumber==TOTAL_QUESTIONS)
                submitButton.setText(Helper.SEND_BTN_SUBMIT);

            String answer1 = questions.get(currentQuestionNumber).getAnswer_a();
            String answer2 = questions.get(currentQuestionNumber).getAnswer_b();
            String answer3 = questions.get(currentQuestionNumber).getAnswer_c();
            String answer4 = questions.get(currentQuestionNumber).getAnswer_d();
            String right = questions.get(currentQuestionNumber).getAnswer_right();
            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);
            answers.add(answer4);
            answers.add(right);
        }
    }

    private void submitFunction() {
        if (compareFragment.getResultFromUser().getText().toString().equals("")) {
            Toast.makeText(this, Helper.MESSAGE_CHOICE_ANSWER, Toast.LENGTH_SHORT).show();
        } else {
            calculateScore();
            if (currentQuestionNumber < TOTAL_QUESTIONS) {
                generateNewQuestion();
            } else {
                moveToDisplayScoreActivity();
            }
        }
    }

    private void calculateScore()
    {
        if (null != findViewById(R.id.questionFragment)) {
            int right = Integer.parseInt(answers.get(4));
            String answer_choice ="";
            String resultFromUser = compareFragment.getResultFromUser().getText().toString();

            switch (right)
            {
                case 1:
                    answer_choice = answers.get(0);
                    break;
                case 2:
                    answer_choice = answers.get(1);
                    break;
                case 3:
                    answer_choice = answers.get(2);
                    break;
                case 4:
                    answer_choice = answers.get(3);
                    break;
            }

            if ( resultFromUser.equals(answer_choice)) {
                score++;
            }
        }
    }

    private void moveToDisplayScoreActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt(Helper.FINAL_SCORE_KEY, score);
        bundle.putInt(Helper.TOTAL_QUESTIONS, TOTAL_QUESTIONS + 1);
        Intent intent = new Intent(CompareActivity.this, DisplayScoreActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        CompareActivity.this.finish();
    }

    private int[] getArrayIndex(int n) {
        int[] a = new int [n];
        Random ran = new Random();
        int i = 0, j, b;
        boolean isDuplicated = false;
        while (i < a.length) {
            // Dùng Math.abs() để tráng số âm
            b = Math.abs(ran.nextInt() % a.length);
            isDuplicated = false;
            // Có thể tách thành 1 phương thức riêng
            for(j = 0; j < i; j++) {
                if(a[j] == b) {
                    isDuplicated = true;
                }
            }
            // Nếu không trùng thì gán và tăng i
            if (!isDuplicated) {
                a[i] = b;
                i++;
            }
            // Nếu trùng thì tiếp tục "dò số"
        }

        for(i = 0; i < a.length; i++) {
            Log.d("abc", String.valueOf(a[i]));
        }
        return a;
    }

    private void loadSoundEffects(String answer)
    {
        if (null != findViewById(R.id.questionFragment))
        {
            int right = Integer.parseInt(answers.get(4));
            String answer_choice ="";

            switch (right)
            {
                case 1:
                    answer_choice = answers.get(0);
                    break;
                case 2:
                    answer_choice = answers.get(1);
                    break;
                case 3:
                    answer_choice = answers.get(2);
                    break;
                case 4:
                    answer_choice = answers.get(3);
                    break;
            }

            Log.d("test", answer_choice);
            Log.d("test", String.valueOf(answer));
            Log.d("test", String.valueOf(right)) ;
            if (answer.equals(answer_choice)) {
                soundAccess.playCorrectAnswer3(1, 1, 1, 0, 0);
            }
            else
                soundAccess.playWrongAnswer3(1, 1, 1, 0, 0);
        }
    }

    @Override
    public void onBackPressed()
    {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            Toast.makeText(this, Helper.EXIT_BTN_BACK_PRESS,
                    Toast.LENGTH_SHORT).show();
        } else {
            CompareActivity.this.finish();
            super.onBackPressed();
        }
        backPressedTime = t;
    }

    private void displayCheckA(String answerDisplay)
    {
        if (null != findViewById(R.id.questionFragment))
        {
            int right = Integer.parseInt(answers.get(4));
            String answer_choice ="";

            switch (right)
            {
                case 1:
                    answer_choice = answers.get(0);
                    break;
                case 2:
                    answer_choice = answers.get(1);
                    break;
                case 3:
                    answer_choice = answers.get(2);
                    break;
                case 4:
                    answer_choice = answers.get(3);
                    break;
            }


            if (answerDisplay.equals(answer_choice)) {
                btnCheckA.setBackground(getResources().getDrawable(R.drawable.correct));
            }
            else
            {
                btnCheckA.setBackground(getResources().getDrawable(R.drawable.wrong));
            }
            btnCheckA.setVisibility(View.VISIBLE);
            btnCheckB.setVisibility(View.INVISIBLE);
            btnCheckC.setVisibility(View.INVISIBLE);
            btnCheckD.setVisibility(View.INVISIBLE);
        }
    }

    private void displayCheckB(String answerDisplay)
    {
        if (null != findViewById(R.id.questionFragment)) {
            int right = Integer.parseInt(answers.get(4));
            String answer_choice = "";

            switch (right) {
                case 1:
                    answer_choice = answers.get(0);
                    break;
                case 2:
                    answer_choice = answers.get(1);
                    break;
                case 3:
                    answer_choice = answers.get(2);
                    break;
                case 4:
                    answer_choice = answers.get(3);
                    break;
            }

            if (answerDisplay.equals(answer_choice)) {
                btnCheckB.setBackground(getResources().getDrawable(R.drawable.correct));
            }
            else
            {
                btnCheckB.setBackground(getResources().getDrawable(R.drawable.wrong));
            }
            btnCheckB.setVisibility(View.VISIBLE);
            btnCheckA.setVisibility(View.INVISIBLE);
            btnCheckC.setVisibility(View.INVISIBLE);
            btnCheckD.setVisibility(View.INVISIBLE);
        }
    }

    private void displayCheckC(String answerDisplay)
    {
        if (null != findViewById(R.id.questionFragment)) {
            int right = Integer.parseInt(answers.get(4));
            String answer_choice = "";

            switch (right) {
                case 1:
                    answer_choice = answers.get(0);
                    break;
                case 2:
                    answer_choice = answers.get(1);
                    break;
                case 3:
                    answer_choice = answers.get(2);
                    break;
                case 4:
                    answer_choice = answers.get(3);
                    break;
            }

            if (answerDisplay.equals(answer_choice)) {
                btnCheckC.setBackground(getResources().getDrawable(R.drawable.correct));
            }
            else
            {
                btnCheckC.setBackground(getResources().getDrawable(R.drawable.wrong));
            }
            btnCheckC.setVisibility(View.VISIBLE);
            btnCheckA.setVisibility(View.INVISIBLE);
            btnCheckB.setVisibility(View.INVISIBLE);
            btnCheckD.setVisibility(View.INVISIBLE);
        }
    }

    private void displayCheckD(String answerDisplay)
    {
        if (null != findViewById(R.id.questionFragment)) {
            int right = Integer.parseInt(answers.get(4));
            String answer_choice = "";

            switch (right) {
                case 1:
                    answer_choice = answers.get(0);
                    break;
                case 2:
                    answer_choice = answers.get(1);
                    break;
                case 3:
                    answer_choice = answers.get(2);
                    break;
                case 4:
                    answer_choice = answers.get(3);
                    break;
            }

            if (answerDisplay.equals(answer_choice)) {
                btnCheckD.setBackground(getResources().getDrawable(R.drawable.correct));
            }
            else
            {
                btnCheckD.setBackground(getResources().getDrawable(R.drawable.wrong));
            }
            btnCheckD.setVisibility(View.VISIBLE);
            btnCheckA.setVisibility(View.INVISIBLE);
            btnCheckB.setVisibility(View.INVISIBLE);
            btnCheckC.setVisibility(View.INVISIBLE);
        }
    }

    private void invisibleCheckAll()
    {
        btnCheckA.setVisibility(View.INVISIBLE);
        btnCheckB.setVisibility(View.INVISIBLE);
        btnCheckC.setVisibility(View.INVISIBLE);
        btnCheckD.setVisibility(View.INVISIBLE);
    }
}
