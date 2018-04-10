package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.User;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignInFragmentPresenter extends MvpPresenter<SignInFragmentView> {
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    SharedPreferences settings;

    @Inject
    FireService fireService;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public SignInFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void enter(String email, String pass) {
        fireService.signIn(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                fireService.getID(email, new FireService.UsersCallBack() {
                    @Override
                    public void onSuccess(List<User> users) {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(Settings.USER_ID, users.get(0).getId());
                        editor.putString(Settings.USERNAME, users.get(0).getUsername());
                        editor.apply();
                        Log.i("code", "userid " + users.get(0).getId());
                        globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast(e.getMessage());
                    }
                });

            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }

    void gotoReg() {
        router.navigateTo(Screens.SIGN_UP_SCREEN);
    }
}
