package com.capshil.esieapp.view.element;

import org.json.JSONException;
import org.json.JSONObject;

public class Beer {

    private String name;
    private String description;
    private int id;

    public Beer(JSONObject jsonBeer){
        try {
            this.name = jsonBeer.getString("name");
            this.description = jsonBeer.getString("description");
            this.id = jsonBeer.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Beer : "+this.name;
    }

}