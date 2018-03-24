package com.mrswimmer.coffeetea.domain.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mrswimmer.coffeetea.data.Basket;
import com.mrswimmer.coffeetea.data.User;

public class FireService {
    private DatabaseReference reference;
    private FirebaseAuth auth;

    public FireService() {
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
    }

    public void getDB(UserCallBack callBack) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child("first"), User.class)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public void getAmountOfUsers(UniCallback callBack) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("statistic").child("users"), DataSnapshot::getValue)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public void signIn(String email, String password, AuthCallBack callBack) {
        RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)
                .map(authResult -> authResult.getUser() != null)
                .take(1)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public void signUp(String email, String password, AuthCallBack callBack) {
        RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)
                .map(authResult -> authResult.getUser() != null)
                .take(1)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public void addUser(String username, String firstName, String lastName, int id) {
        reference.child("users/"+id).setValue("13");
    }

    public interface UserCallBack {
        void onSuccess(User user);
        void onError(Throwable e);
    }

    public interface AuthCallBack {
        void onSuccess(boolean success);
        void onError(Throwable e);
    }

    public interface UniCallback {
        void onSuccess(Object o);
        void onError(Throwable e);
    }
}