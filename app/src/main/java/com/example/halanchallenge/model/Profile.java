package com.example.halanchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.halanchallenge.R;

public class Profile implements Parcelable {

    private String username;
    private String image;
    private String name;
    private String email;
    private String phone;

    protected Profile(Parcel in) {
        username = in.readString();
        image = in.readString();
        name = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
    }

    @BindingAdapter("profilePic")
    public static void loadImage(ImageView view, String path) {
        Glide.with(view.getContext())
                .load(path)
                .placeholder(R.drawable.circle)
                .into(view);
    }
}
