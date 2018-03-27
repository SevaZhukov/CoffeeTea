package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in;

import android.content.SharedPreferences;
import android.transition.Scene;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Availability;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;

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
                globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showErrorToast(":(");
            }
        });
    }

    void gotoReg() {
        //fireService.getProducts();
        router.navigateTo(Screens.SIGN_UP_SCREEN);
    }

    void goToMain() {
        /*DatabaseReference newProd = reference.child("products").push();
        ArrayList<String> images = new ArrayList<>();
        images.add("url1");
        images.add("url2");
        ArrayList<Availability> availabilities = new ArrayList<>();
        availabilities.add(new Availability(2, "0123"));
        availabilities.add(new Availability(4, "456"));
        newProd.setValue(new Product(newProd.getKey(), 100, "ok", "coffee1", 150, images, availabilities));
        Log.i("code", "OK");*/
        globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
    }
}
