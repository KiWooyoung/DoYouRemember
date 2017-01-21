package com.omjoonkim.doyouremember.model;

public class HomeReceiveChildData {
    private boolean receiveChecked;
    private String debtorName;
    private int priceIndividual;

    public HomeReceiveChildData(String debtorName, int priceIndividual) {
        this.debtorName = debtorName;
        this.priceIndividual = priceIndividual;
        this.receiveChecked = false;
    }

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
}
