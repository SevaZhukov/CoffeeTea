package com.mrswimmer.coffeetea.domain.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.DataSnapshotMapper;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.data.model.product.Product;
import com.mrswimmer.coffeetea.data.model.User;

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
                    Log.i("code", "get User " + user.getFirstName());
                });
    }

    public void getSaleProducts(ProductsCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").orderByChild("newCost").startAt(0), DataSnapshotMapper.listOf(Product.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getID(String email, UserCallBack callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").orderByChild("mail").equalTo(email), User.class)
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getProduct(String id, ProductCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").child(id), Product.class)
                .subscribe(callback::onSuccess, callback::onError);
    }

    public boolean checkLogIn() {
        return null == auth.getCurrentUser();
    }

    public void getReviews(String id, boolean shop, ReviewsCallback callback) {
        String dir;
        if(shop) {
            dir = "shops";
        } else {
            dir = "products";
        }
        RxFirebaseDatabase.observeSingleValueEvent(reference.child(dir).child(id).child("reviews"), DataSnapshotMapper.listOf(Review.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getShops(ShopsCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("shops"), DataSnapshotMapper.listOf(Shop.class))
                .subscribe(callback::onSuccess, callback::onError);
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

    public interface ProductCallback {
        void onSuccess(Product product);

        void onError(Throwable e);
    }

    public interface ReviewsCallback {
        void onSuccess(List<Review> reviews);

        void onError(Throwable e);
    }
    public interface ShopsCallback {
        void onSuccess(List<Shop> shops);

        void onError(Throwable e);
    }

    public void getProducts(boolean sale, ProductsCallback callback) {
        if (sale)
            RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").orderByChild("newCost").startAt(1), DataSnapshotMapper.listOf(Product.class))
                    .subscribe(callback::onSuccess, callback::onError);
        else
            RxFirebaseDatabase.observeSingleValueEvent(reference.child("products"), DataSnapshotMapper.listOf(Product.class))
                    .subscribe(callback::onSuccess, callback::onError);
    }

    public void getProductsWithParams(int typeId, ProductsCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").orderByChild("typeId").equalTo(typeId), DataSnapshotMapper.listOf(Product.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void addInBasket() {

    }
}