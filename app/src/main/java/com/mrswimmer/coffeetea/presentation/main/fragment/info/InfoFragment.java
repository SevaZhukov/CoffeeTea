package com.mrswimmer.coffeetea.presentation.main.fragment.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;
import com.mrswimmer.coffeetea.presentation.base.BaseView;

import butterknife.BindView;

public class InfoFragment extends BaseFragment implements BaseView {
    @BindView(R.id.info_text)
    TextView text;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        int info = bundle.getInt("text");
        text.setText(info);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_info;
    }
}
