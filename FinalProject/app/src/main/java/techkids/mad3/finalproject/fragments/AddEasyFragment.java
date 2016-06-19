package techkids.mad3.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.mad3.finalproject.R;
import techkids.mad3.finalproject.drawing.CalculationCustomView;

public class AddEasyFragment extends Fragment {
    private View view;
    private int firstNumber;
    private int secondNumber;

    public AddEasyFragment() {
    }

    public AddEasyFragment(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
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
        return view;
    }
}
