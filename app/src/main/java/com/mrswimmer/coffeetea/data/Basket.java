package com.mrswimmer.coffeetea.data;

public class Basket
{
    private String product;

    private String shop;

    private String quantity;

    public Basket(String product, String shop, String quantity) {
        this.product = product;
        this.shop = shop;
        this.quantity = quantity;
    }

    public Basket() {
    }

    public String getProduct ()
    {
        return product;
    }

    public void setProduct (String product)
    {
        this.product = product;
    }

    public String getShop ()
    {
        return shop;
    }

    public void setShop (String shop)
    {
        this.shop = shop;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [product = "+product+", shop = "+shop+", quantity = "+quantity+"]";
    }
}
