package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in;

import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInFragment extends BaseFragment implements SignInFragmentView {
    @InjectPresenter
    SignInFragmentPresenter presenter;

    @ProvidePresenter
    public SignInFragmentPresenter presenter() {
        return new SignInFragmentPresenter();
    }

    String email, password;

    @BindView(R.id.sign_in_email)
    EditText editEmail;
    @BindView(R.id.sign_in_password)
    EditText editPass;

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }*/

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_in;
    }

    @OnClick(R.id.sign_in_enter)
    void onEnterClick() {
        presenter.goToMain();
        email = editEmail.getText().toString();
        password = editPass.getText().toString();
        //enter();
    }

    @OnClick(R.id.sign_in_reg)
    void onRegClick() {
        presenter.gotoReg();
    }

    /*@Override
    public void showErrorToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }*/

    void enter() {
        if (checkOnFillingFields()) {
            presenter.enter(email, password);
        } else {
            showToast("Запоните все поля");
            //showErrorToast("Заполните все поля!");
        }
    }

    boolean checkOnFillingFields() {
        if (email.equals("") || password.equals(""))
            return false;
        return true;
    }
}
