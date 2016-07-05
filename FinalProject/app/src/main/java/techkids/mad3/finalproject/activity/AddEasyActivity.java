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
import java.util.Random;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;
import techkids.mad3.finalproject.fragments.CalculateEasyFragment;
import techkids.mad3.finalproject.models.SoundAccess;
import techkids.mad3.finalproject.selfDefinedStructure.Pair;

public class AddEasyActivity extends AppCompatActivity implements View.OnClickListener {
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 9;
    private final int TOTAL_ANSWERS = 4;
    private final int TOTAL_QUESTIONS = 10;

    private Random random = new Random();
    private ArrayList<Pair> questionAsked = new ArrayList<>();

    private TextView questionNumber;
    private Button submitButton, answerButtonA, answerButtonB, answerButtonC, answerButtonD;
    private int currentQuestionNumber = 0;
    private Pair newValues;
    private ArrayList<Integer> answers = new ArrayList<>();
    private int score = 0;

    private FragmentTransaction fragmentTransaction;
    private CalculateEasyFragment calculateEasyFragment;
    private SoundAccess soundAccess;
    private ArrayList<Integer> indexs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        soundAccess.initCorrectAnswer3(getApplicationContext(), R.raw.dung3);
    }

    private int random(int low, int high) {
        return low + random.nextInt(high);
    }

    private void generateNewQuestion() {
        if (null != findViewById(R.id.questionFragment)) {
            currentQuestionNumber++;
            questionNumber.setText("Câu " + currentQuestionNumber);

            newValues = generateRandomNumbersAndAnswer();
            questionAsked.add(newValues);

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            calculateEasyFragment = new CalculateEasyFragment(newValues.getFirstValue(), newValues.getSecondValue(), "+");
            fragmentTransaction.setCustomAnimations(R.anim.left_to_right, 0);
            fragmentTransaction.replace(R.id.questionFragment, calculateEasyFragment).commit();

            answerButtonA.setText("A. " + answers.get(0));
            answerButtonB.setText("B. " + answers.get(1));
            answerButtonC.setText("C. " + answers.get(2));
            answerButtonD.setText("D. " + answers.get(3));
        }
    }

    private Pair generateRandomNumbersAndAnswer() {
        boolean repeated = false;
        Pair newValues;
        if (answers != null) {
            answers.clear();
        }
        do {
            repeated = false;
            int sum = random(2 * MIN_VALUE, MAX_VALUE);
            int firstNumber = random(MIN_VALUE, sum - MIN_VALUE);
            int secondNumber = sum - firstNumber;
            newValues = new Pair(firstNumber, secondNumber);
            answers.add(sum);
            for (Pair askedQuestion : questionAsked) {
                if (askedQuestion.equals(newValues)) {
                    repeated = true;
                }
            }
            if (repeated) {
                answers.clear();
            }
        } while (repeated);

        do {
            do {
                repeated = false;
                Integer value = random(MIN_VALUE, MAX_VALUE);
                for (Integer existAnswer : answers) {
                    if (value.equals(existAnswer)) {
                        repeated = true;
                    }
                }
                if (!repeated) {
                    answers.add(value);
                }
            } while (repeated);
        } while (answers.size() < TOTAL_ANSWERS);
        return newValues;
    }

    private void calculateScore() {
        int resultFromUser = Integer.parseInt(calculateEasyFragment.getResultFromUser().getText().toString());
        int firstNumber = newValues.getFirstValue();
        int secondNumber = newValues.getSecondValue();
        if (resultFromUser == firstNumber + secondNumber) {
            score++;
        }
    }

    private void submitFunction() {
        if (calculateEasyFragment.getResultFromUser().getText().toString().equals("")) {
            Toast.makeText(this, "Choose an answer!", Toast.LENGTH_SHORT).show();
        } else {
            calculateScore();
            if (currentQuestionNumber < TOTAL_QUESTIONS) {
                generateNewQuestion();
            } else {
                moveToDisplayScoreActivity();
            }
        }
    }

    private void moveToDisplayScoreActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt(Helper.FINAL_SCORE_KEY, score);
        bundle.putInt(Helper.TOTAL_QUESTIONS, TOTAL_QUESTIONS);
        Intent intent = new Intent(AddEasyActivity.this, DisplayScoreActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                submitFunction();
                break;
            case R.id.answerButtonA:
                calculateEasyFragment.getResultFromUser().setText(String.valueOf(answers.get(0)));
                soundAccess.playCorrectAnswer3(1, 1, 1, 0, 1);
                getArrayIndex(4);

                break;
            case R.id.answerButtonB:
                calculateEasyFragment.getResultFromUser().setText(String.valueOf(answers.get(1)));
                break;
            case R.id.answerButtonC:
                calculateEasyFragment.getResultFromUser().setText(String.valueOf(answers.get(2)));
                break;
            case R.id.answerButtonD:
                calculateEasyFragment.getResultFromUser().setText(String.valueOf(answers.get(3)));
                break;
        }
    }

    private int[] getArrayIndex(int n) {
        int[] a = new int [n];
        Random ran = new Random();
        int i = 0, j, b;
        boolean isDuplicated = false;
        while (i < a.length) {
            // Dùng Math.abs() để tráng số âm và 0
            b = Math.abs(ran.nextInt() % a.length) + 1;
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
            // Nếu trùng thì tếp tục "dò số"
        }

        for(i = 0; i < a.length; i++) {
            Log.d("abc", String.valueOf(a[i]));
        }
        return a;
    }
}
