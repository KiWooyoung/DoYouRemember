package com.omjoonkim.doyouremember.model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class HomeReceiveParentData implements Parent<HomeReceiveChildData> {
    private String title;
    private String debtors;
    private List<HomeReceiveChildData> homeReceiveChildDataList;
    private int totalPrice;
    private int checkedCount;

    public HomeReceiveParentData(String title, String debtors, List<HomeReceiveChildData> homeReceiveChildDataList) {
        this.title = title;
        this.debtors = debtors;
        this.homeReceiveChildDataList = homeReceiveChildDataList;
        sumPrice();
        checkedCount = 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDebtors(String debtors) {
        this.debtors = debtors;
    }

    public String getTitle() {
        return title;
    }

    public String getDebtors() {
        return debtors;
    }

    public HomeReceiveChildData getHomeReceiveChildData(int position) {
        return homeReceiveChildDataList.get(position);
    }

    @Override
    public List<HomeReceiveChildData> getChildList() {
        return homeReceiveChildDataList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    private void sumPrice() {
        totalPrice = 0;
        for (int i = 0; i < homeReceiveChildDataList.size(); i++) {
            totalPrice += homeReceiveChildDataList.get(i).getPriceIndividual();
        }
    }

    private void sumChecked(){
        for(int i=0; i<homeReceiveChildDataList.size(); i++){
            if(homeReceiveChildDataList.get(i).isReceiveChecked()){
                checkedCount += 1;
            }
        }
    }
}
