package com.example.user.tabapplication;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonListViewFragment extends Fragment {
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.listview_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView)getActivity().findViewById(R.id.listView);
        JSONArray jsonArray = null;
        try {
            String jsonData = this.readAsset("tab1.json");
            jsonArray = new JSONArray(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list = new ArrayList<String>();

        try {
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject)jsonArray.get(i);
                String text = object.getString("text");
                list.add(text);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }

    public String readAsset(String filename) throws IOException {
        String result = "";
        InputStream is = getActivity().getAssets().open("tab1.json");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
