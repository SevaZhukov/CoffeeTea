package com.mrswimmer.coffeetea.presentation.main.fragment.order.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private ArrayList<Order> orders = new ArrayList<>();
    public static String qr;

    @Inject
    @Local
    Router localRouter;
    @Inject
    @Global
    Router globalRouter;
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;

    public OrderAdapter(ArrayList<Order> orders) {
        this.orders = orders;
        App.getComponent().inject(this);
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.id.setText(order.getId());
        holder.cost.setText(order.getSum()+"");
        holder.date.setText(order.getDateString());
        holder.delete.setOnClickListener(v -> deleteOrder(order));
        holder.show.setOnClickListener(v -> showOrder(order));
        String text=order.getId();// Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            holder.qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        holder.qr.setOnClickListener(v -> {
            qr = order.getId();
            globalRouter.navigateTo(Screens.ZOOM_QR, holder.id);
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    void deleteOrder(Order order) {
        String userId = settings.getString(Settings.USER_ID, "0");
        fireService.deleteOrder(userId, order);
        localRouter.navigateTo(Screens.ORDERS_SCREEN);
    }

    void showOrder(Order order) {
        localRouter.navigateTo(Screens.BASKET_OF_ORDER_SCREEN, order.getId());
    }
}
