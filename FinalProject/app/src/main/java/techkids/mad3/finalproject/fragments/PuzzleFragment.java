package techkids.mad3.finalproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import techkids.mad3.finalproject.R;


/**
 * Created by TrungNT on 7/9/2016.
 */
public class PuzzleFragment extends Fragment {
    private View view;
    String contentPuzzle;
    private TextView resultFromUser;

    private PuzzleFragment() {
    }

    public PuzzleFragment(String contentPuzzle) {
       this.contentPuzzle = contentPuzzle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == container) {
            return null;
        }
        view = inflater.inflate(R.layout.fragment_question_type_puzzle, container, false);
        TextView tvContentPuzzle = (TextView) view.findViewById(R.id.tvContentPuzzle);
        tvContentPuzzle.setText(contentPuzzle);
        resultFromUser = (TextView) view.findViewById(R.id.resultFromUser);
        return view;
    }

    public TextView getResultFromUser() {
        return resultFromUser;
    }
}
