package com.example.user.tabapplication.data;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by user on 2015-12-29.
 */
public class WelfareJson {
    public static JSONArray getArray(Activity activity) {
        JSONArray jsonArray = null;
        try {
            String jsonData = WelfareJson.readAsset("tab1.json", activity);
            jsonArray = new JSONArray(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static String readAsset(String filename, Activity activity) throws IOException {
        String result = "";
        InputStream is = activity.getAssets().open("tab1.json");
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
