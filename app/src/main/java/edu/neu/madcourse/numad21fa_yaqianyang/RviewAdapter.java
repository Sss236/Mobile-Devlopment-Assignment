package edu.neu.madcourse.numad21fa_yaqianyang;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<ItemCard> itemList;
    private ItemClickListener listener;

    public RviewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcard, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        ItemCard currentItem = itemList.get(position);

        holder.itemName.setText(currentItem.getItemName());
        holder.itemLink.setText(currentItem.getItemLink());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

     public void addItem(ItemCard card){
        itemList.add(card);
        this.notifyDataSetChanged();
     }
}
