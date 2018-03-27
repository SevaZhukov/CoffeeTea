package com.mrswimmer.coffeetea.presentation.main.fragment.filter.recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.di.qualifier.Local;

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
                .inflate(R.layout.item_mode, parent, false);
        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product product = products.get(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
