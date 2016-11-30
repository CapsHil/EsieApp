package com.capshil.esieapp.controller.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.capshil.esieapp.R;
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
import java.util.ArrayList;


public class BeerFragment extends Fragment {

    public BeerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Beer> beers = new ArrayList<Beer>();
        ArrayList<String> beersName = new ArrayList<String>();

        try {
            String content = requestContent("http://binouze.fabrigli.fr/bieres.json");
            System.out.println("COUCOU "+content);
            JSONObject json = new JSONObject(content);
            JSONObject dataObject = json.getJSONObject("data");
            JSONArray items = dataObject.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject beerObject = items.getJSONObject(i);
                Beer beer = new Beer(beerObject.getString("name"),
                        beerObject.getString("description"),
                        beerObject.getInt("country_id"),
                        beerObject.getInt("category_id"));
                beers.add(beer);
                beersName.add(beerObject.getString("name"));
            }

        } catch (JSONException e) {
            // manage exceptions
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, beersName);

        View rootView = inflater.inflate(R.layout.fragment_beers, container, false);
        ListView listv = (ListView) rootView.findViewById(R.id.list_beers);
        listv.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public String requestContent(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        String result = null;
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = null;
        InputStream instream = null;

        try {
            response = httpclient.execute(httpget);
            System.out.println("RESPONSE "+response);
            HttpEntity entity = response.getEntity();
            System.out.println("ENTITY "+entity);

            if (entity != null) {
                instream = entity.getContent();
                System.out.println("INSTREAM "+instream);
                result = convertStreamToString(instream);
            }

        } catch (Exception e) {
            // manage exceptions
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (Exception exc) {

                }
            }
        }

        return result;
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }

        return sb.toString();
    }
}
