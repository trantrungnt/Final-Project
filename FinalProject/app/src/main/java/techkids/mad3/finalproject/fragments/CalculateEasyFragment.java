package techkids.mad3.finalproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.drawing.CalculationCustomView;

public class CalculateEasyFragment extends Fragment {
    private View view;
    private int firstNumber;
    private int secondNumber;
    private String operator;
    private TextView resultFromUser;

    private CalculateEasyFragment() {
    }

    public CalculateEasyFragment(int firstNumber, int secondNumber, String operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == container) {
            return null;
        }
        view = inflater.inflate(R.layout.fragment_question_type_1, container, false);
        CalculationCustomView calculationCustomView = (CalculationCustomView) view.findViewById(R.id.calculationCustomView);
        calculationCustomView.setFirstNumber(firstNumber);
        calculationCustomView.setSecondNumber(secondNumber);
        calculationCustomView.setOperator(operator);
        resultFromUser = (TextView) view.findViewById(R.id.resultFromUser);
        return view;
    }

    public TextView getResultFromUser() {
        return resultFromUser;
    }
}
