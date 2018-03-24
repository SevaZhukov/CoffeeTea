package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInFragment extends MvpAppCompatFragment implements SignInFragmentView {
    @InjectPresenter
    SignInFragmentPresenter presenter;

    @ProvidePresenter
    public SignInFragmentPresenter presenter() {
        return presenter;
    }

    String sUsername, sPassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    void sendData() {
        if(checkOnFillingFields()) {
            presenter.enter(sUsername, sPassword);
        } else {
            showErrorToast("Заполните все поля!");
        }
    }
    boolean checkOnFillingFields() {
        if (sUsername.equals("") || sPassword.equals("")) {
            return false;
        }
        return true;
    }
}
