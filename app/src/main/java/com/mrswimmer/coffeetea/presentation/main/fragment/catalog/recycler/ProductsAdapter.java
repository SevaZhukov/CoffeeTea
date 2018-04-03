package com.mrswimmer.coffeetea.presentation.main.fragment.catalog.recycler;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {
    private ArrayList<Product> products = new ArrayList<>();
    private Context context;

    @Inject
    @Local
    Router localRouter;

    public ProductsAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
        App.getComponent().inject(this);
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product product = products.get(position);
        holder.name.setText(product.getName());
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
        }
        //holder.cost.setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
