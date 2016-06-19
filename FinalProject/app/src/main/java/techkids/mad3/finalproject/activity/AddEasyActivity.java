package techkids.mad3.finalproject.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.fragments.AddEasyFragment;
import techkids.mad3.finalproject.selfDefinedStructure.Pair;

public class AddEasyActivity extends AppCompatActivity implements View.OnClickListener {
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 9;
    private final int TOTAL_ANSWERS = 4;

    private Random random = new Random();
    private ArrayList<Pair> questionAsked = new ArrayList<>();

    private TextView questionNumber;
    private Button submitButton, answerButtonA, answerButtonB, answerButtonC, answerButtonD;
    private int currentQuestionNumber = 0;
    private ArrayList<Integer> answers = new ArrayList<>();

    private FragmentTransaction fragmentTransaction;
    private AddEasyFragment addEasyFragment;

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
    }

    private int random(int low, int high) {
        return low + random.nextInt(high);
    }

    private void generateNewQuestion() {
        if (null != findViewById(R.id.questionFragment)) {
            currentQuestionNumber++;
            questionNumber.setText("Câu " + currentQuestionNumber);

            Pair newValues = generateRandomNumbersAndAnswer();
            questionAsked.add(newValues);
            fragmentTransaction = getFragmentManager().beginTransaction();
            addEasyFragment = new AddEasyFragment(newValues.getFirstValue(), newValues.getSecondValue(), "+");
            fragmentTransaction.replace(R.id.questionFragment, addEasyFragment).commit();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                generateNewQuestion();
                break;
            case R.id.answerButtonA:
                addEasyFragment.getResultFromUser().setText(String.valueOf(answers.get(0)));
                break;
        }
    }
}
