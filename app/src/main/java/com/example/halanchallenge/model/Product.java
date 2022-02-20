package com.example.halanchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.halanchallenge.R;

import java.util.List;

public class Product implements Parcelable {

    private String id;
    private String name_ar;
    private String deal_description;
    private String brand;
    private String image;
    private String name_en;
    private int price;
    private List<String> images;


    public Product(String id, String name_ar, String deal_description, String brand, String image, String name_en,
                   int price, List<String> images) {
        this.id = id;
        this.name_ar = name_ar;
        this.deal_description = deal_description;
        this.brand = brand;
        this.image = image;
        this.name_en = name_en;
        this.price = price;
        this.images = images;
    }
    protected Product(Parcel in) {
        id = in.readString();
        name_ar = in.readString();
        deal_description = in.readString();
        brand = in.readString();
        image = in.readString();
        name_en = in.readString();
        price = in.readInt();
        images = in.createStringArrayList();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public String getDeal_description() {
        return deal_description;
    }

    public void setDeal_description(String deal_description) {
        this.deal_description = deal_description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name_ar);
        dest.writeString(deal_description);
        dest.writeString(brand);
        dest.writeString(image);
        dest.writeString(name_en);
        dest.writeInt(price);
        dest.writeStringList(images);
    }

    @BindingAdapter("imagePath")
    public static void loadProductImage(ImageView view, String path) {
        Glide.with(view.getContext())
                .load(path)
                .placeholder(R.drawable.circle)
                .into(view);
    }


@BindingAdapter("imageUrl")
public static void loadImage(ImageView view, String path) {
    Glide.with(view.getContext())
            .load(path)
            .placeholder(R.drawable.circle)
            .into(view);
}
}
