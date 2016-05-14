package techkids.mad3.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.mad3.finalproject.R;

public class AddEasyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == container) {
            return null;
        }
        return inflater.inflate(R.layout.fragment_question_type_1, container, false);
    }
}
