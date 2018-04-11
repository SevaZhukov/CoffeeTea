package com.mrswimmer.coffeetea.domain.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.DataSnapshotMapper;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.data.model.Availability;
import com.mrswimmer.coffeetea.data.model.Product;
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

    public void getID(String email, UsersCallBack callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").orderByChild("mail").equalTo(email), DataSnapshotMapper.listOf(User.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getProduct(String id, ProductCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").child(id), Product.class)
                .subscribe(callback::onSuccess, callback::onError);
    }

    public boolean checkLogIn() {
        return null != auth.getCurrentUser();
    }

    public void getReviews(String id, boolean shop, ReviewsCallback callback) {
        String dir;
        if (shop) {
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

    public void getProducts(boolean sale, ProductsCallback callback) {
        if (sale)
            RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").orderByChild("newCost").startAt(1), DataSnapshotMapper.listOf(Product.class))
                    .subscribe(callback::onSuccess, callback::onError);
        else
            RxFirebaseDatabase.observeSingleValueEvent(reference.child("products"), DataSnapshotMapper.listOf(Product.class))
                    .subscribe(callback::onSuccess, callback::onError);
    }

    public void putProductInBasket(String userId, ProductInBasket productInBasket) {
        DatabaseReference newProd = reference.child("users").child(userId).child("basket").push();
        productInBasket.setId(newProd.getKey());
        newProd.setValue(productInBasket);
    }

    public void getBasket(String userId, BasketCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(userId).child("basket"), DataSnapshotMapper.listOf(ProductInBasket.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void checkOnExistThisProductInBasket(String userId, ProductInBasket productInBasket, BasketCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(userId).child("basket").orderByChild("productId").equalTo(productInBasket.getProductId()), DataSnapshotMapper.listOf(ProductInBasket.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void addInExistProductInBasket(String id, int count, String userId) {
        DatabaseReference newProd = reference.child("users").child(userId).child("basket").child(id).child("count");
        newProd.setValue(count);
    }

    public void deleteProd(String prodId, String shopId, int count) {
        Log.i("code", "del  " + prodId + " " + shopId + " " + count);
        getAllAvails(prodId, new AvailabilityCallback() {
            @Override
            public void onError(Throwable e) {
                Log.i("code", "all avails error " + e.getMessage());
            }

            @Override
            public void onSuccess(List<Availability> availabilities) {
                Log.i("code", "all avails " + availabilities.size());
                boolean exist = false;
                for (int i = 0; i < availabilities.size(); i++) {
                    Availability availability = availabilities.get(i);
                    if (availability.getShopId().equals(shopId)) {
                        if (availabilities.get(i).getQuantity() - count == 0) {
                            availabilities.remove(i);
                        } else
                            availabilities.get(i).setQuantity(availability.getQuantity() - count);
                        break;
                    }
                }
                DatabaseReference avail = reference.child("products").child(prodId).child("availabilities");
                avail.setValue(availabilities);
            }
        });
    }

    public void getAllAvails(String prodId, AvailabilityCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("products").child(prodId).child("availabilities"), DataSnapshotMapper.listOf(Availability.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void delFromBasket(String userId, String id, ProductInBasket product) {
        restoreProducts(product);
        DatabaseReference prod = reference.child("users").child(userId).child("basket").child(id);
        prod.removeValue();
    }

    public void restoreProducts(ProductInBasket product) {
        getAllAvails(product.getProductId(), new AvailabilityCallback() {
            @Override
            public void onError(Throwable e) {
                Log.i("code", "all avails error " + e.getMessage());
            }

            @Override
            public void onSuccess(List<Availability> availabilities) {
                Log.i("code", "all avails " + availabilities.size());
                boolean exist = false;
                for (int i = 0; i < availabilities.size(); i++) {
                    Availability availability = availabilities.get(i);
                    if (availability.getShopId().equals(product.getShopId())) {
                        availabilities.get(i).setQuantity(availability.getQuantity() + product.getCount());
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    availabilities.add(new Availability(product.getCount(), product.getShopId()));
                }
                DatabaseReference avail = reference.child("products").child(product.getProductId()).child("availabilities");
                avail.setValue(availabilities);
            }
        });
    }

    public void clearBasket(String userId) {
        DatabaseReference prod = reference.child("users").child(userId).child("basket");
        prod.removeValue();
    }

    public void makeOrder(Order order) {
        DatabaseReference dref = reference.child("orders").push();
        order.setId(dref.getKey());
        dref.setValue(order);
    }

    public void getOrders(String userId, OrdersCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("orders").orderByChild("userId").equalTo(userId), DataSnapshotMapper.listOf(Order.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getBasketOfOrders(String userId, String orderId, BasketCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("orders").child(userId).child(orderId).child("products"), DataSnapshotMapper.listOf(ProductInBasket.class))
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void deleteOrder(String userId, Order order) {
        for (int i = 0; i < order.getProducts().size(); i++) {
            restoreProducts(order.getProducts().get(i));
        }
        DatabaseReference or = reference.child("orders").child(userId).child(order.getId());
        or.removeValue();
    }

    public void signOut() {
        auth.signOut();
    }

    public void setReview(String id, Review review) {
        getReviews(id, false, new ReviewsCallback() {
            boolean exist = false;
            @Override
            public void onSuccess(List<Review> reviews) {
                for (int i=0; i<reviews.size(); i++) {
                    if(reviews.get(i).getUserId().equals(review.getUserId())) {
                        reviews.get(i).setMark(review.getMark());
                        reviews.get(i).setDescription(review.getDescription());
                        exist = true;
                    }
                }
                if(!exist) {
                    reviews.add(review);
                }
                DatabaseReference ref = reference.child("products").child(id).child("reviews");
                ref.setValue(reviews);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public interface UsersCallBack {
        void onSuccess(List<User> users);

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

    public interface AvailabilityCallback {
        void onError(Throwable e);

        void onSuccess(List<Availability> availabilities);
    }

    public interface BasketCallback {
        void onSuccess(List<ProductInBasket> products);

        void onError(Throwable e);
    }

    public interface OrdersCallback {
        void onSuccess(List<Order> orders);

        void onError(Throwable e);
    }
}