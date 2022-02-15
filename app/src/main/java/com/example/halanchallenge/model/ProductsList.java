package com.example.halanchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ProductsList implements Parcelable{
    private String status;
    private List<Product> products;

    protected ProductsList(Parcel in) {
        status = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(products);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductsList> CREATOR = new Creator<ProductsList>() {
        @Override
        public ProductsList createFromParcel(Parcel in) {
            return new ProductsList(in);
        }

        @Override
        public ProductsList[] newArray(int size) {
            return new ProductsList[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static Creator<ProductsList> getCREATOR() {
        return CREATOR;
    }
}