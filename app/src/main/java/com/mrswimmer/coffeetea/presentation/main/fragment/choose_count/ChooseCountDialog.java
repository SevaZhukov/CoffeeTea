package com.mrswimmer.coffeetea.presentation.main.fragment.choose_count;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCountDialog extends AppCompatActivity {

    public static int count;
    int maxCount;
    public static boolean nextPressed;
    @BindView(R.id.dialog_choose_choose_count)
    TextView countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_count);
        count = 1;
        nextPressed = false;
        ButterKnife.bind(this);
        Intent intent = getIntent();
        maxCount = intent.getIntExtra("max", 1);
    }

    @OnClick(R.id.dialog_choose_up)
    void onUpClick() {
        if (count < maxCount) {
            count++;
            countText.setText(count + "");
        }
    }

    @OnClick(R.id.dialog_choose_down)
    void onDownClick() {
        if (count > 1) {
            count--;
            countText.setText(count + "");
        }
    }

    @OnClick(R.id.dialog_choose_next)
    void onNextClick() {
        nextPressed = true;
        finish();
    }

    @OnClick(R.id.dialog_choose_back)
    void onBackClick() {
        finish();
    }

}
