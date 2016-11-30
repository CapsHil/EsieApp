package com.capshil.esieapp.view.element;

public class DrawerItem {

    private int nameId;
    private int imgId;

    public DrawerItem(int nameId, int imgId) {
        super();
        this.nameId = nameId;
        this.imgId = imgId;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}