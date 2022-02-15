package com.example.halanchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListActivityDataItem implements Parcelable {
    private LoginResponse loginResponse;
    private ProductsList productsList;

    public ListActivityDataItem(LoginResponse loginResponse, ProductsList productsList) {
        this.loginResponse = loginResponse;
        this.productsList = productsList;
    }
    protected ListActivityDataItem(Parcel in) {
        loginResponse = in.readParcelable(LoginResponse.class.getClassLoader());
        productsList = in.readParcelable(ProductsList.class.getClassLoader());
    }

    public static final Creator<ListActivityDataItem> CREATOR = new Creator<ListActivityDataItem>() {
        @Override
        public ListActivityDataItem createFromParcel(Parcel in) {
            return new ListActivityDataItem(in);
        }

        @Override
        public ListActivityDataItem[] newArray(int size) {
            return new ListActivityDataItem[size];
        }
    };

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public ProductsList getProductsList() {
        return productsList;
    }

    public void setProductsList(ProductsList productsList) {
        this.productsList = productsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(loginResponse, flags);
        dest.writeParcelable(productsList, flags);
    }
}
