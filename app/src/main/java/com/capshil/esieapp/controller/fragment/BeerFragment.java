package com.capshil.esieapp.controller.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.capshil.esieapp.R;
import com.capshil.esieapp.library.HTTPRequest;
import com.capshil.esieapp.library.JsonParser;
import com.capshil.esieapp.view.element.Beer;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import android.app.ProgressDialog;
import android.util.Log;

import static android.R.attr.progress;


public class BeerFragment extends Fragment {

    private static final String TEST_URL                   = "http://binouze.fabrigli.fr/bieres.json";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";
    private static final String TAG = "AATestFragment";

    ProgressDialog progress;
    private TextView ourTextView;

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    private Beer beer = null;
    public BeerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beers, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ourTextView = (TextView)getActivity().findViewById(R.id.name);
        getContent();
    }

    private void getContent()
    {
        try
        {
            HttpGet httpGet = new HttpGet(new URI(TEST_URL));
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
            //ourTextView.setText(response);
            try {
                JSONArray entries = new JSONArray(response);

                String x = "JSON parsed.\nThere are [" + entries.length() + "]\n\n";

                int i;
                for (i=0;i<entries.length();i++)
                {
                    JSONObject post = null;
                    try {
                        post = entries.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    x += "------------\n";
                    try {
                        x += "Date:" + post.getString("name") + "\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ourTextView.setText(x);
            } catch (Exception je) {
                ourTextView.setText("Error w/file: " + je.getMessage());
            }
        }
    };
}
