package com.example.user.tabapplication;

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

public class FilteredJsonListViewFragment extends Fragment {
    private ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    String tag;

    private FilteredJsonListViewFragment() {
    }

    public FilteredJsonListViewFragment(String _tag, int _id) {
        super();
        this.tag = _tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_layout, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = new ArrayList<String>();
        JSONArray jsonArray = WelfareJson.getArray(getActivity());
        try {
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject)jsonArray.get(i);
                JSONArray tags = object.getJSONArray("tags");
                boolean contained = false;
                for (int j=0;j<tags.length();j++) {
                    if (tags.getString(j).equals(this.tag)) contained = true;
                }
                if (tag != null && !this.tag.isEmpty() && !contained) continue;
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

    private static final String ARG_PAGE_NUMBER = "page_number";

    public static FilteredJsonListViewFragment newInstance(String tag, int page) {
        FilteredJsonListViewFragment fragment = new FilteredJsonListViewFragment(tag, page);
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }
}
