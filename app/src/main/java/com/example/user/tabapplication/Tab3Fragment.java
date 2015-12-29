package com.example.user.tabapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.tabapplication.data.WelfareJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tab3Fragment extends Fragment {
    public ArrayList<String> all_tags = new ArrayList<String>();
    public Map<String, ArrayList<JSONObject>> dataMap = new HashMap<String, ArrayList<JSONObject> >();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.tab3_layout, container, false);
    }

    public void initData() throws JSONException {
        JSONArray jsonArray = WelfareJson.getArray(getActivity());
        for (int i=0;i<jsonArray.length();i++) {
            JSONObject obj = (JSONObject)jsonArray.get(i);
            JSONArray tags = obj.getJSONArray("tags");
            for (int j=0;j<tags.length();j++) {
                String tag = tags.getString(j);
                if (!dataMap.containsKey(tag)) {
                    dataMap.put(tag, new ArrayList<JSONObject>());
                    all_tags.add(tag);
                }
                dataMap.get(tag).add(obj);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initData();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab3_tab_layout);
        for (String key : all_tags) {
            tabLayout.addTab(tabLayout.newTab().setText(key));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.tab3_pager);
        final Tab3PagerAdapter adapter = new Tab3PagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
