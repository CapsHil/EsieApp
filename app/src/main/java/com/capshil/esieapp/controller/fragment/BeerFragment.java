package com.capshil.esieapp.controller.fragment;

import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.capshil.esieapp.R;
import com.capshil.esieapp.view.adapter.BeersAdapter;
import com.capshil.esieapp.library.HTTPRequest;
import com.capshil.esieapp.view.element.Beer;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.util.Log;


public class BeerFragment extends ListFragment {

    private static final String BEER_URL                   = "http://binouze.fabrigli.fr/bieres.json";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";
    private static final String TAG = "AATestFragment";

    ProgressDialog progress;

    private Beer beer = null;
    public BeerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beers, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getContent(BEER_URL);
    }

    private void getContent(String url)
    {
        try
        {
            HttpGet httpGet = new HttpGet(new URI(url));
            HTTPRequest task = new HTTPRequest(getActivity(), ACTION_FOR_INTENT_CALLBACK);
            task.execute(httpGet);
            progress = ProgressDialog.show(getActivity(), "Getting Data ...", "Waiting For Results...", true);
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter(ACTION_FOR_INTENT_CALLBACK));
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (progress != null)
                progress.dismiss();
            String response = intent.getStringExtra(HTTPRequest.HTTP_RESPONSE);
            TextView numberOfBeer = (TextView) getActivity().findViewById(R.id.number_of_beers);
            try {
                JSONArray entries = new JSONArray(response);
                numberOfBeer.setText("There are " + entries.length() + " beers\n\n");

                ArrayList<Beer> arrayOfBeers = new ArrayList<Beer>();
                BeersAdapter adapter = new BeersAdapter(getActivity(), arrayOfBeers);
                ListView listView = (ListView) getActivity().findViewById(android.R.id.list);
                listView.setAdapter(adapter);
                Beer beer = null;
                for (int i = 0; i < entries.length(); i++) {
                    JSONObject post;
                    post = entries.getJSONObject(i);
                    beer = new Beer(post);
                    adapter.add(beer);
                }
            } catch (Exception je) {
                numberOfBeer.setText("Error w/file: " + je.getMessage());
            }
        }
    };
}
