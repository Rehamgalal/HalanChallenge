package com.example.halanchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginResponse implements Parcelable {

    private String status;
    private String token;
    private Profile profile;

    protected LoginResponse(Parcel in) {
        status = in.readString();
        token = in.readString();
        profile = in.readParcelable(Profile.class.getClassLoader());
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(token);
        dest.writeParcelable(profile, flags);
    }
}
