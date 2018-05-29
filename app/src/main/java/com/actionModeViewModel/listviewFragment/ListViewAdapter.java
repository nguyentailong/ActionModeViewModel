package com.actionModeViewModel.listviewFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.actionmode.viewmodel.R;
import com.actionModeViewModel.ItemModel;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ItemModel> mItemModels;


    ListViewAdapter(Context context) {
        this.context = context;
    }

    void updateData(List<ItemModel> itemModels) {
        this.mItemModels = itemModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mItemModels != null) {
            return mItemModels.size();
        } else {
            return 0;
        }
    }

    @Override
    public ItemModel getItem(int position) {
        return mItemModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_row, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.sub_title = convertView.findViewById(R.id.sub_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(mItemModels.get(position).getTitle());
        holder.sub_title.setText(mItemModels.get(position).getSubTitle());


        /** Change background color of the selected items in list view  **/
        convertView.setBackgroundColor(
                mItemModels.get(position).isSelected() ? Color.LTGRAY : Color.TRANSPARENT);

        return convertView;
    }

    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        mItemModels.get(position).toggle();
        notifyDataSetChanged();

    }

    private class ViewHolder {
        TextView title, sub_title;
    }
}
