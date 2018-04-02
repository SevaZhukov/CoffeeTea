package com.mrswimmer.coffeetea.presentation.main.fragment.basket.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class ProductsInBasketAdapter extends RecyclerView.Adapter<ProductsInBasketViewHolder> {
    private ArrayList<ProductInBasket> products = new ArrayList<>();
    private Context context;
    boolean orders;

    @Inject
    @Local
    Router localRouter;
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;

    public ProductsInBasketAdapter(ArrayList<ProductInBasket> products, Context context, boolean orders) {
        this.products = products;
        this.context = context;
        this.orders = orders;
        App.getComponent().inject(this);
    }

    @Override
    public ProductsInBasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_in_basket, parent, false);
        return new ProductsInBasketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsInBasketViewHolder holder, int position) {
        ProductInBasket product = products.get(position);
        holder.ratingBar.setRating(product.getProductRate());
        holder.adress.setText(product.getAdress());
        holder.city.setText(product.getCity());
        holder.name.setText(product.getName());
        holder.count.setText(product.getCount() + "");
        if(!orders) {
            holder.delete.setOnClickListener(v -> {
                fireService.delFromBasket(settings.getString(Settings.USER_ID, "0"), product.getId(), product);
                localRouter.replaceScreen(Screens.BASKET_SCREEN);
            });
        }

        /*holder.name.setText(product.getName());
        holder.type.setText(product.getType());
        holder.kind.setText(product.getKind());
        holder.inStock.setText(product.getInStock());
        holder.ratingBar.setRating(product.getRate());
        holder.cost.setText(product.getCostString());
        Picasso.with(context)
                .load(product.getImages().get(0))
                .into(holder.image);
        holder.itemView.setOnClickListener(v -> localRouter.navigateTo(Screens.PRODUCT_SCREEN, product.getId()));
        if (product.getNewCost() > 0) {
            holder.newCost.setText(product.getNewCostString());
            holder.cost.setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }*/
        //holder.cost.setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
