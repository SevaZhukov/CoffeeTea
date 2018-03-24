package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_up;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.User;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;
import com.mrswimmer.coffeetea.presentation.main.MainActivity;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

import static android.content.ContentValues.TAG;

@InjectViewState
public class SignUpFragmentPresenter extends MvpPresenter<SignUpFragmentView> {
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

    public SignUpFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void signUp(String email, String pass) {

        fireService.signUp(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                getViewState().getUserData();
                //globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "error reg" + e);
                getViewState().showErrorToast(":((");
            }
        });
    }
    void addUser(String username, String firstName, String lastName) {
        fireService.getAmountOfUsers(new FireService.UniCallback() {
            @Override
            public void onSuccess(Object o) {
                Log.i("code", "success " + o);
                int id = (int) o;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.child("users").child(String.valueOf(id)).setValue("12");
                //fireService.addUser(username, firstName, lastName, id);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "error " + e);
            }
        });
    }
}
