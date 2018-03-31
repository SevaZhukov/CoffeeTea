package com.mrswimmer.coffeetea.domain.service;

import com.mrswimmer.coffeetea.data.model.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterService {
    List<Product> products;


    public FilterService() {

    }

    public ArrayList<Product> getFilteredProducts(int type, int sort, boolean inStock, boolean[] kinds, List<Product> products) {
        ArrayList<Product> filteredProductList = new ArrayList<>();
        switch (sort) {
            case 0:
                Collections.sort(products, (o1, o2) -> o1.getCost() - o2.getCost());
                break;
            case 1:
                Collections.sort(products, (o1, o2) -> o2.getCost() - o1.getCost());
                break;
            case 2:
                Collections.sort(products, (o1, o2) -> o1.getRate() - o2.getRate());
                break;
            case 3:
                Collections.sort(products, (o1, o2) -> o2.getRate() - o1.getRate());
                break;
        }
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getTypeId() == type) {
                for (int j = 0; j < kinds.length; j++) {
                    if (kinds[j] && product.getKindId() == j) {
                        if (inStock && product.getAvailabilities().size() > 0) {
                            filteredProductList.add(product);
                        } else
                            filteredProductList.add(product);
                        break;
                    }
                }
            }
        }
        return filteredProductList;
    }
}
