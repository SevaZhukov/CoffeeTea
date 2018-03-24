package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.coffeetea.data.base.BaseView;

interface SignInFragmentView extends MvpView {
    void showErrorToast(String error);
}
