package com.example.jahed.bossassistant;

public class HandletoggolButton {


    private int id;
    private int positon;
    private String texEmlName;
    private boolean isSelected=true;
    public HandletoggolButton() {
    }

    public HandletoggolButton(int id, int positon, String texEmlName) {
        this.id = id;
        this.positon = positon;
        this.texEmlName = texEmlName;
    }

    public HandletoggolButton(int positon, String texEmlName) {
        this.positon = positon;
        this.texEmlName = texEmlName;
    }

    public String getTexEmlName() {
        return texEmlName;
    }

    public void setTexEmlName(String texEmlName) {
        this.texEmlName = texEmlName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }
    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
