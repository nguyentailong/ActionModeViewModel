package com.actionModeViewModel.recyclerFragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionmode.viewmodel.R;
import com.actionModeViewModel.ItemModel;
import com.actionModeViewModel.recyclerFragment.RecyclerViewAdapter.RecyclerViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<ItemModel> mItemModels;

    RecyclerViewAdapter() {
    }

    void updateData(List<ItemModel> itemModels) {
        this.mItemModels = itemModels;
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        mItemModels.get(position).toggle();
        notifyItemChanged(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(
            ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_row, viewGroup, false);
        return new RecyclerViewHolder(mainGroup);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder,
                                 int position) {
        //Setting text over text view
        holder.title.setText(mItemModels.get(position).getTitle());
        holder.sub_title.setText(mItemModels.get(position).getSubTitle());

        holder.itemView.setBackgroundColor(
                mItemModels.get(position).isSelected() ? Color.LTGRAY : Color.TRANSPARENT);

    }

    @Override
    public int getItemCount() {
        return (null != mItemModels ? mItemModels.size() : 0);

    }

    public ItemModel getItem(int position) {
        return mItemModels.get(position);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {


        TextView title, sub_title;


        RecyclerViewHolder(View view) {
            super(view);


            this.title = view.findViewById(R.id.title);
            this.sub_title = view.findViewById(R.id.sub_title);

        }
    }


}