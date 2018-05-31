package com.mrswimmer.coffeetea.presentation.main.fragment.order.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    TextView id;
    TextView date;
    TextView cost;
    Button show;
    ImageView delete;
    ImageView qr;
    public OrderViewHolder(View v) {
        super(v);
        qr = v.findViewById(R.id.item_order_qr);
        id = v.findViewById(R.id.item_order_id);
        date = v.findViewById(R.id.item_order_date);
        cost = v.findViewById(R.id.item_order_cost);
        show = v.findViewById(R.id.item_order_show);
        delete = v.findViewById(R.id.item_order_delete);
    }
}
