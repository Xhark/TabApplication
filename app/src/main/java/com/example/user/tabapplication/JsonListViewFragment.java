package com.example.user.tabapplication;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.tabapplication.data.WelfareJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonListViewFragment extends Fragment {
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listview_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView)getActivity().findViewById(R.id.listView);
        list = new ArrayList<String>();
        JSONArray jsonArray = WelfareJson.getArray(getActivity());
        try {
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject)jsonArray.get(i);
                String text = object.getString("title") + " | " + object.getString("tel");
                list.add(text);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}
