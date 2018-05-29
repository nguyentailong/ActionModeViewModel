package com.actionModeViewModel;

import java.io.Serializable;

public class ItemModel implements Serializable {

    /*  Model class for List and Recycler Items  */
    private String title, subTitle;
    private boolean selected;

    ItemModel(String title, String subTitle, boolean selected) {
        this.title = title;
        this.subTitle = subTitle;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void toggle() {
        selected = !selected;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    @Override public boolean equals(Object obj) {
        return title.equals(((ItemModel) obj).title);
    }
}
