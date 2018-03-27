package com.mrswimmer.coffeetea.data.model;

public class ProductInBasket
{
    private String productId;

    private String shopId;

    private String quantity;

    public ProductInBasket(String product, String shopId, String quantity) {
        this.productId = product;
        this.shopId = shopId;
        this.quantity = quantity;
    }

    public ProductInBasket() {
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }
}
