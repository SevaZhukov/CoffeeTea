package com.mrswimmer.coffeetea.presentation.main.fragment.shop.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;
import com.mrswimmer.coffeetea.presentation.main.fragment.product.ProductFragmentPresenter;
import com.mrswimmer.coffeetea.presentation.main.fragment.product.choose_count.ChooseCountDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {
    private final Context context;
    private ArrayList<Shop> shops = new ArrayList<>();

    @Inject
    @Local
    Router localRouter;

    @Inject
    FireService fireService;

    @Inject
    SharedPreferences settings;

    boolean choose = false;

    public ShopAdapter(ArrayList<Shop> shops, boolean choose, Context context) {
        this.shops = shops;
        this.choose = choose;
        this.context = context;
        App.getComponent().inject(this);
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.adress.setText(shop.getAdress());
        holder.city.setText(shop.getCity());
        holder.hours.setText(shop.getHours());
        SpannableString content = new SpannableString(shop.getReviewsString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        holder.reviews.setText(content);
        holder.ratingBar.setRating(shop.getRate());
        Picasso.with(context)
                .load(shop.getImages().get(0))
                .into(holder.image);
        holder.reviews.setOnClickListener(v -> localRouter.navigateTo(Screens.REVIEWS_SCREEN_FOR_SHOP, shop.getId()));
        if (choose)
            holder.itemView.setOnClickListener(v -> {
                Log.i("code", "touch");
                addInBasket(shop);
            });
    }

    void addInBasket(Shop shop) {
        Product product = ProductFragmentPresenter.curProduct;
        ProductInBasket productInBasket = new ProductInBasket(product.getId(), shop.getId(), product.getName(), shop.getAdress(), shop.getCity(), product.getRate(), product.getCost(), product.getNewCost(), ChooseCountDialog.count);
        String userId = settings.getString(Settings.USER_ID, "0");
        fireService.checkOnExistThisProductInBasket(userId, productInBasket, new FireService.BasketCallback() {
            @Override
            public void onSuccess(List<ProductInBasket> products) {
                boolean exist = false;
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getShopId().equals(productInBasket.getShopId())) {
                        fireService.addInExistProductInBasket(products.get(i).getId(), productInBasket.getCount()+products.get(i).getCount(), userId);
                        fireService.deleteProd(product.getId(), shop.getId(), productInBasket.getCount());
                        exist = true;
                        localRouter.navigateTo(Screens.BASKET_SCREEN);
                    }
                }
                Log.i("code", "bool exist" + exist);
                if (!exist) {
                    fireService.putProductInBasket(userId, productInBasket);
                    fireService.deleteProd(product.getId(), shop.getId(), productInBasket.getCount());
                    localRouter.navigateTo(Screens.BASKET_SCREEN);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }
}
