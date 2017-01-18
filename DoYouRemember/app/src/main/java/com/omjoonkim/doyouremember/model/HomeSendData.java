package com.omjoonkim.doyouremember.model;

public class HomeSendData {

    String title;
    String creditor;
    String account;
    int price;
    int deadline;

    public HomeSendData(String title, String creditor, String account, int price, int deadline){
        this.title = title;
        this.creditor = creditor;
        this.account = account;
        this.price = price;
        this.deadline = deadline;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDeadline(int dealine) {
        this.deadline = dealine;
    }

    public String getCreditor() {
        return creditor;
    }

    public String getAccount() {
        return account;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getDeadline() {
        return deadline;
    }
}
