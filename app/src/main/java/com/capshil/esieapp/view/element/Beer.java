package com.capshil.esieapp.view.element;

import org.json.JSONException;
import org.json.JSONObject;

public class Beer {

    private String name;
    private String description;
    private int countryId;
    private int categoryId;
    private String country;
    private String category;

    public Beer(){
    }

    public Beer(JSONObject jsonBeer){
        try {
            this.name = jsonBeer.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        this.description = description;
//        this.countryId = countryId;
//        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String link) {
        this.country = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String picture) {
        this.category = picture;
    }

    @Override
    public String toString() {
        return "Beer : "+this.name;
    }

}