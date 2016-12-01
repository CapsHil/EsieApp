package com.capshil.esieapp.controller.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.capshil.esieapp.R;
import com.capshil.esieapp.library.JsonParser;
import com.capshil.esieapp.view.element.Beer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class BeerFragment extends Fragment {

    private Beer beer = null;
    public BeerFragment() {
        System.out.println("COUCO");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String url = "http://10.0.2.2/JSON/";

        //JSON Node Names
        final String TAG_NAME = "name";

        JSONArray user = null;

        // Creating new JSON Parser
        JsonParser jParser = new JsonParser();

        // Getting JSON from URL
        JSONObject json = jParser.getJSONFromUrl(url);

        try {
            // Getting JSON Array
            user = json.getJSONArray("bieres");
            JSONObject c = user.getJSONObject(0);

            // Storing  JSON item in a Variable
            String name = c.getString(TAG_NAME);

            View rootView = inflater.inflate(R.layout.fragment_beers, container, false);

            //Importing TextView
            final TextView name1 = (TextView)rootView.findViewById(R.id.name);

            //Set JSON Data in TextView

            name1.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.fragment_beers, container, false);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, beersName);
//
//        View rootView = inflater.inflate(R.layout.fragment_beers, container, false);
//        ListView listv = (ListView) rootView.findViewById(R.id.list_beers);
//        listv.setAdapter(adapter);
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
