package com.omjoonkim.doyouremember.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeReceiveChildData implements Parcelable {
    private boolean receiveChecked;
    private String debtorName;
    private int priceIndividual;

    public HomeReceiveChildData(String debtorName, int priceIndividual) {
        this.debtorName = debtorName;
        this.priceIndividual = priceIndividual;
        this.receiveChecked = false;
    }

    protected HomeReceiveChildData(Parcel in) {
        receiveChecked = in.readByte() != 0;
        debtorName = in.readString();
        priceIndividual = in.readInt();
    }

    public static final Creator<HomeReceiveChildData> CREATOR = new Creator<HomeReceiveChildData>() {
        @Override
        public HomeReceiveChildData createFromParcel(Parcel in) {
            return new HomeReceiveChildData(in);
        }

        @Override
        public HomeReceiveChildData[] newArray(int size) {
            return new HomeReceiveChildData[size];
        }
    };

    public void setReceiveChecked(boolean receiveChecked) {
        this.receiveChecked = receiveChecked;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public void setPriceIndividual(int priceIndividual) {
        this.priceIndividual = priceIndividual;
    }

    public boolean isReceiveChecked() {
        return receiveChecked;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public int getPriceIndividual() {
        return priceIndividual;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (receiveChecked ? 1 : 0));
        dest.writeString(debtorName);
        dest.writeInt(priceIndividual);
    }
}
