package com.example.afreen.speakupfinal;

/**
 * Created by sejal on 3/11/16.
 */
public class Country {

    String code = null;
    String name = null;
    String image=null;
    boolean selected = false;

    public Country(String url,String code, String name, boolean selected) {
        super();
        this.code = code;
        this.name = name;
        this.image=url;
        this.selected = selected;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String geturl() {
        return image;
    }


    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}