package com.mrswimmer.coffeetea.presentation.main.fragment.poduct;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.product.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductFragment extends MvpAppCompatFragment implements ProductFragmentView {
    @InjectPresenter
    ProductFragmentPresenter presenter;

    @ProvidePresenter
    public ProductFragmentPresenter presenter() {
        return new ProductFragmentPresenter();
    }

    @BindView(R.id.prod_name)
    TextView name;
    @BindView(R.id.prod_weight)
    TextView weight;
    @BindView(R.id.prod_in_stock)
    TextView inStock;
    @BindView(R.id.prod_type)
    TextView type;
    @BindView(R.id.prod_kind)
    TextView kind;
    @BindView(R.id.prod_cost)
    TextView cost;
    @BindView(R.id.prod_new_cost)
    TextView newCost;
    @BindView(R.id.prod_description)
    TextView description;
    @BindView(R.id.prod_find_shops)
    TextView findShops;
    @BindView(R.id.prod_find_reviews)
    TextView findReviews;
    @BindView(R.id.prod_arrow_shops)
    ImageView arrowShops;
    @BindView(R.id.prod_arrow_reviews)
    ImageView arrowReviews;
    @BindView(R.id.prod_image)
    ImageView image;
    @BindView(R.id.prod_bottom)
    ConstraintLayout bottomLayout;
    @BindView(R.id.prod_bottom_in_basket)
    TextView inBasket;
    @BindView(R.id.prod_bottom_in_one_click)
    TextView inOneClick;
    @BindView(R.id.prod_rate)
    RatingBar rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        String id = bundle.getString("id");
        presenter.getProduct(id);
        Log.i("code", "prod " + id);
    }

    @Override
    public void setProduct(Product product) {
        name.setText(product.getName());
        type.setText(product.getType());
        kind.setText(product.getKind());
        weight.setText(product.getWeight()+"");
        cost.setText(product.getCostString());
        if(product.getNewCost()>0) {
            cost.setPaintFlags(cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            newCost.setText(product.getNewCostString());
        }
        rate.setRating(product.getRate());
        description.setText(product.getDescription());
        findShops.setText(product.findShops());
        findReviews.setText(product.findReviews());
        presenter.showReviews(product.getReviews());
    }
}
