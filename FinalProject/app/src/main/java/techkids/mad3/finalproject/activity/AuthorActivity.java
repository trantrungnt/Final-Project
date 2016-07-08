package techkids.mad3.finalproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import techkids.mad3.finalproject.R;

/**
 * Created by TrungNT on 7/8/2016.
 */
public class AuthorActivity extends AppCompatActivity implements OnClickListener{
    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        initComponents();
    }

    private void initComponents() {
        btnClose = (Button) this.findViewById(R.id.btnExit);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnClose:
                AuthorActivity.this.finish();
                break;
        }
    }
}
