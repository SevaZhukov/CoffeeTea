package com.mrswimmer.coffeetea.domain.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.DataSnapshotMapper;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class FireService {
    private DatabaseReference reference;
    private FirebaseAuth auth;

    public FireService() {
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
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

    public void getUser(String key) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(key), User.class)
                .subscribe(user -> {
                    Log.i("code", "get User " + user.getFirst_name());
                });
    }

    public interface UserCallBack {
        void onSuccess(User user);

        void onError(Throwable e);
    }

    public interface AuthCallBack {
        void onSuccess(boolean success);

        void onError(Throwable e);
    }

    public interface ProductsCallback {
        void onSuccess(List<Product> products);

        void onError(Throwable e);
    }

    public void getProducts(ProductsCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products"), DataSnapshotMapper.listOf(Product.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getProductsWithParams(int typeId, ProductsCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").orderByChild("typeId").equalTo(typeId), DataSnapshotMapper.listOf(Product.class))
                .subscribe(callback::onSuccess, callback::onError);
    }
}