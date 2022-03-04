package edu.neu.madcourse.numad21fa_yaqianyang;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener {

    private final String itemName;
    private final String itemLink;

    //Constructor
    public ItemCard(String itemName, String itemLink) {
        this.itemName = itemName;
        this.itemLink = itemLink;
    }

    public String getItemLink() {
        return itemLink;
    }

    public String getItemName() {
        return itemName;
    }


    @Override
    public void onItemClick(int position) {
    return;
    }

}