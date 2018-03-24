package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_up;

import com.arellomobile.mvp.MvpView;

public interface SignUpFragmentView extends MvpView{
    void showErrorToast(String error);
    void getUserData();
}
