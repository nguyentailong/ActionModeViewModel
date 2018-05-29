package com.actionModeViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ItemModel>> selectedItems;
    private final MutableLiveData<List<ItemModel>> recyclerViewItems;
    private final MutableLiveData<List<ItemModel>> listViewItems;

    public MainViewModel() {
        selectedItems = new MutableLiveData<>();
        listViewItems = new MutableLiveData<>();
        recyclerViewItems = new MutableLiveData<>();
        selectedItems.setValue(new ArrayList<>());
        listViewItems.setValue(getListViewList());
        recyclerViewItems.setValue(getRecyclerViewList());
    }
    void reset()
    {
        selectedItems.setValue(new ArrayList<>());
        listViewItems.setValue(getListViewList());
        recyclerViewItems.setValue(getRecyclerViewList());
    }
    public MutableLiveData<List<ItemModel>> getSelectedItems() {
        return selectedItems;
    }

    public MutableLiveData<List<ItemModel>> getRecyclerViewItems() {
        return recyclerViewItems;
    }

    public MutableLiveData<List<ItemModel>> getListViewItems() {
        return listViewItems;
    }

    private List<ItemModel> getRecyclerViewList() {
        List<ItemModel> itemModels = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            itemModels.add(new ItemModel("RecyclerView " + i, "Sub Title " + i, false));

        }
        return itemModels;
    }

    private List<ItemModel> getListViewList() {
        List<ItemModel> itemModels = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            itemModels.add(new ItemModel("ListView " + i, "Sub Title " + i, false));

        }
        return itemModels;
    }


    @Override
    protected void onCleared() {

    }
}
