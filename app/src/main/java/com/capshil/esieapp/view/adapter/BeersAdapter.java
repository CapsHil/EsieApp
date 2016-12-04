package com.capshil.esieapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capshil.esieapp.R;
import com.capshil.esieapp.view.element.Beer;

import java.util.ArrayList;

public class BeersAdapter extends ArrayAdapter<Beer> {
    public BeersAdapter(Context context, ArrayList<Beer> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Beer beer = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_beers, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        name.setText("Name : "+beer.getName());
        description.setText("Description : "+beer.getDescription());
        return convertView;
    }
}