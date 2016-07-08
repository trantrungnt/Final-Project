package techkids.mad3.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.constants.Helper;
import techkids.mad3.finalproject.fragments.CalculateEasyFragment;
import techkids.mad3.finalproject.selfDefinedStructure.Pair;

/**
 * Created by TrungNT on 6/20/2016.
 */
public class SubstractEasyActivity extends AppCompatActivity implements View.OnClickListener {
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 9;
    private final int TOTAL_ANSWERS = 4;
    private final int TOTAL_QUESTIONS = 10;

    private Random random = new Random();
    private ArrayList<Pair> questionAsked = new ArrayList<>();

    private TextView questionNumber;
    private Button btnSubmit, btnAnswerA, btnAnswerB, btnAnswerC, btnAnswerD;
    private int currentQuestionNumber = 0;
    private Pair newValues;
    private ArrayList<Integer> answers = new ArrayList<>();
    private int score = 0;
    private CalculateEasyFragment calculateEasyFragment;
    private FragmentTransaction fragmentTransaction;


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
        questionNumber = (TextView) this.findViewById(R.id.questionNumber);

        btnAnswerA = (Button) this.findViewById(R.id.answerButtonA);
        btnAnswerB = (Button) this.findViewById(R.id.answerButtonB);
        btnAnswerC = (Button) this.findViewById(R.id.answerButtonC);
        btnAnswerD = (Button) this.findViewById(R.id.answerButtonD);
        btnSubmit = (Button) this.findViewById(R.id.submitButton);

        btnAnswerA.setOnClickListener(this);
        btnAnswerB.setOnClickListener(this);
        btnAnswerC.setOnClickListener(this);
        btnAnswerD.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.submitButton:
                submitFunction();
                break;
            case R.id.answerButtonA:
                calculateEasyFragment.getResultFromUser().setText(String.valueOf(answers.get(0)));
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

    private int random(int low, int high) {
        return low + random.nextInt(high);
    }

    private Pair generateRandomNumbersAndAnswer() {
        boolean repeated = false;
        Pair newValues;
        if (answers != null) {
            answers.clear();
        }
        do {
            repeated = false;
            int firstNumber = random(MIN_VALUE, MAX_VALUE);
            int secondNumber = random(MIN_VALUE, MAX_VALUE);
            if (firstNumber > secondNumber) {
                int tmp = firstNumber;
                firstNumber = secondNumber;
                secondNumber = tmp;
            }
            int subStractionNumber;
            if (firstNumber > secondNumber)
                subStractionNumber = firstNumber - secondNumber;
            else
                subStractionNumber = secondNumber - firstNumber;

            newValues = new Pair(firstNumber, secondNumber);

            answers.add(subStractionNumber);
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
        int subStractNumber;

        if (firstNumber > secondNumber)
            subStractNumber = firstNumber - secondNumber;
        else
            subStractNumber = secondNumber - firstNumber;

        if (resultFromUser == subStractNumber) {
            score++;
        }
    }

    private void generateNewQuestion() {
        if (null != findViewById(R.id.questionFragment)) {
            currentQuestionNumber++;
            questionNumber.setText("Câu " + currentQuestionNumber);

            newValues = generateRandomNumbersAndAnswer();
            questionAsked.add(newValues);
            fragmentTransaction = getSupportFragmentManager() .beginTransaction();

            if (newValues.getFirstValue() > newValues.getSecondValue())
                calculateEasyFragment = new CalculateEasyFragment(newValues.getFirstValue(), newValues.getSecondValue(), "-");
            else
                calculateEasyFragment = new CalculateEasyFragment(newValues.getSecondValue(), newValues.getFirstValue(), "-");

            fragmentTransaction.setCustomAnimations(R.anim.left_to_right, 0);
            fragmentTransaction.replace(R.id.questionFragment, calculateEasyFragment).commit();

            btnAnswerA.setText("A. " + answers.get(0));
            btnAnswerB.setText("B. " + answers.get(1));
            btnAnswerC.setText("C. " + answers.get(2));
            btnAnswerD.setText("D. " + answers.get(3));
        }
    }

    private void submitFunction() {
        if (calculateEasyFragment.getResultFromUser().getText().toString().equals("")) {
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

    private void moveToDisplayScoreActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt(Helper.FINAL_SCORE_KEY, score);
        bundle.putInt(Helper.TOTAL_QUESTIONS, TOTAL_QUESTIONS);
        Intent intent = new Intent(SubstractEasyActivity.this, DisplayScoreActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
