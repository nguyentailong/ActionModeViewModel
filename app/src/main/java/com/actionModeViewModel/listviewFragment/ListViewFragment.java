package com.actionModeViewModel.listviewFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionmode.viewmodel.R;
import com.actionModeViewModel.ItemModel;
import com.actionModeViewModel.MainActivity;
import com.actionModeViewModel.MainViewModel;

import java.util.List;

public class ListViewFragment extends Fragment {
    private View view;
    private ListViewAdapter adapter;
    private ListView listView;
    private MainViewModel mMainViewModel;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.list_view_fragment, container, false);
        populateListView();
        implementListViewClickListeners();
        //22
        mMainViewModel.getListViewItems().observe(this, adapter::updateData);
        return view;
    }

    //Populate ListView with dummy data
    private void populateListView() {
        listView = view.findViewById(R.id.list_view);
        adapter = new ListViewAdapter(getActivity());
        listView.setAdapter(adapter);
        //checkout 3
    }

    //Implement item click and long click over listview
    private void implementListViewClickListeners() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            //If ActionMode not null select item
            if (((MainActivity) getActivity()).mActionMode != null) {
                adapter.toggleSelection(position);//Toggle the selection
                ItemModel itemModel = adapter.getItem(position);
                List<ItemModel> itemModels = mMainViewModel.getSelectedItems().getValue();
                if (itemModels.contains(itemModel)) {
                    itemModels.remove(itemModel);
                } else {
                    itemModels.add(itemModel);
                }
                mMainViewModel.getSelectedItems().setValue(itemModels);
            }
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (((MainActivity) getActivity()).mActionMode == null) {
                ((MainActivity) getActivity()).startActionMode();
            }
            adapter.toggleSelection(position);//Toggle the selection
            ItemModel itemModel = adapter.getItem(position);
            List<ItemModel> itemModels = mMainViewModel.getSelectedItems().getValue();
            if (itemModels.contains(itemModel)) {
                itemModels.remove(itemModel);
            } else {
                itemModels.add(itemModel);
            }
            mMainViewModel.getSelectedItems().setValue(itemModels);
            return true;
        });
    }
}
