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
 * Created by TrungNT on 7/10/2016.
 */
public class CompareFragment extends Fragment {
    private View view;
    private String contentCompare;
    private TextView resultFromUser;

    private CompareFragment()
    {

    }

    public CompareFragment(String contentCompare)
    {
        this.contentCompare = contentCompare;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == container) {
            return null;
        }
        view = inflater.inflate(R.layout.fragment_question_type_compare, container, false);
        TextView tvContentCompare = (TextView) view.findViewById(R.id.tvContentCompare);
        tvContentCompare.setText(contentCompare);
        resultFromUser = (TextView) view.findViewById(R.id.resultFromUser);
        return view;
    }

    public TextView getResultFromUser() {
        return resultFromUser;
    }
}
