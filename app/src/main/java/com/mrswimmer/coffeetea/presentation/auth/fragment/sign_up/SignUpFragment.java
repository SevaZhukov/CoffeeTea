package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends MvpAppCompatFragment implements SignUpFragmentView {
    @InjectPresenter
    SignUpFragmentPresenter presenter;

    @ProvidePresenter
    public SignUpFragmentPresenter presenter() {
        return new SignUpFragmentPresenter();
    }

    @BindView(R.id.sign_up_email)
    EditText editEmail;
    @BindView(R.id.sign_up_password)
    EditText editPass;
    @BindView(R.id.sign_up_username)
    EditText editUsername;
    @BindView(R.id.sign_up_first_name)
    EditText editFirstName;
    @BindView(R.id.sign_up_last_name)
    EditText editLastName;
    @BindView(R.id.sign_up_city_spinner)
    Spinner spinner;

    String email, pass, username, firstName, lastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.sign_up_reg)
    void onRegClick() {
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();
        username = editUsername.getText().toString();
        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();
        signUp();
        //presenter.signUp(email, pass);
    }

    void signUp() {
        if (checkOnFillingFields()) {
            presenter.signUp(email, pass);
        } else {
            showErrorToast("Заполните все поля!");
        }
    }
    @Override
    public void showErrorToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserData() {
        presenter.addUser(username, firstName, lastName);
    }

    boolean checkOnFillingFields() {
        if (email.equals("") || pass.equals("") || username.equals("") || firstName.equals("") || lastName.equals(""))
            return false;
        return true;
    }
}
