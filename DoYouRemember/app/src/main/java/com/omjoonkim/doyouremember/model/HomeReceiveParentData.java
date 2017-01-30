package com.omjoonkim.doyouremember.model;

import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;

import java.util.List;

public class HomeReceiveParentData extends MultiCheckExpandableGroup {

    private String title;
    private int totalPrice;
    private int checkedCount;
    private List<HomeReceiveChildData> homeReceiveChildDataList;

    public HomeReceiveParentData(String title, List<HomeReceiveChildData> homeReceiveChildDataList) {
        super(title, homeReceiveChildDataList);
        this.title = title;
        this.homeReceiveChildDataList = homeReceiveChildDataList;
        sumPrice();
        sumChecked();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getCheckedCount() {
        return checkedCount;
    }

    private void sumPrice() {
        totalPrice = 0;
        for (int i = 0; i < homeReceiveChildDataList.size(); i++) {
            totalPrice += homeReceiveChildDataList.get(i).getPriceIndividual();
        }
    }

    public void sumChecked(){
        checkedCount = 0;
        for(int i = 0; i < homeReceiveChildDataList.size(); i++){
            if(homeReceiveChildDataList.get(i).isReceiveChecked()){
                checkedCount += 1;
            }
        }
    }
}
