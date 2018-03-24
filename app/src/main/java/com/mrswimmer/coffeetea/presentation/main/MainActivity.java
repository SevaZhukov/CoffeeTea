package com.mrswimmer.coffeetea.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.User;
import com.mrswimmer.coffeetea.domain.service.FireService;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Inject
    FireService fireService;

    @OnClick(R.id.button)
    void onClick() {
        fireService.getDB(new FireService.UserCallBack() {
            @Override
            public void onSuccess(User user) {
                //Log.i("code", user.getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", e+"");

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        ButterKnife.bind(this);

    }
}
