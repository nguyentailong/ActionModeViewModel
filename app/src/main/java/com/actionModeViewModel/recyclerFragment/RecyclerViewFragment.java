package com.actionModeViewModel.recyclerFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionmode.viewmodel.R;
import com.actionModeViewModel.ItemModel;
import com.actionModeViewModel.MainActivity;
import com.actionModeViewModel.MainViewModel;

import java.util.List;


public class RecyclerViewFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private MainViewModel mMainViewModel;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        populateRecyclerView();
        implementRecyclerViewClickListeners();
        mMainViewModel.getRecyclerViewItems().observe(this, adapter::updateData);
        return view;
    }

    //Populate ListView with dummy data
    private void populateRecyclerView() {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }

    //Implement item click and long click over recycler view
    private void implementRecyclerViewClickListeners() {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        if (((MainActivity) getActivity()).mActionMode != null) {
                            adapter.toggleSelection(position);
                            ItemModel itemModel = adapter.getItem(position);
                            List<ItemModel> itemModels =
                                    mMainViewModel.getSelectedItems().getValue();
                            if (itemModels.contains(itemModel)) {
                                itemModels.remove(itemModel);
                            } else {
                                itemModels.add(itemModel);
                            }
                            mMainViewModel.getSelectedItems().setValue(itemModels);
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        if (((MainActivity) getActivity()).mActionMode == null) {
                            ((MainActivity) getActivity()).startActionMode();
                        }
                        adapter.toggleSelection(position);
                        ItemModel itemModel = adapter.getItem(position);
                        List<ItemModel> itemModels = mMainViewModel.getSelectedItems().getValue();
                        if (itemModels.contains(itemModel)) {
                            itemModels.remove(itemModel);
                        } else {
                            itemModels.add(itemModel);
                        }
                        mMainViewModel.getSelectedItems().setValue(itemModels);
                    }
                }));
    }
}