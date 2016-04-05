package com.example.juanandres.myapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by juanandres on 05/04/16.
 */
public class JSONAdapter extends BaseAdapter {

    JSONArray friends;
    Activity activity;

    public JSONAdapter(JSONArray array, Activity a){
        this.friends = array;
        this.activity = a;
    }
    @Override
    public int getCount() {
        return friends.length();
    }

    @Override
    public Object getItem(int position) {
        JSONObject result = null;
        try {
            result = friends.getJSONObject(position);
        }catch (JSONException jse){
            jse.printStackTrace();
        }
        return result;
    }

    @Override
    public long getItemId(int position) {
        long id = -1;

        try {
            JSONObject result = friends.getJSONObject(position);
            id = result.getLong("id");
        }catch (JSONException jse){
            jse.printStackTrace();
        }

        return id;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = activity.getLayoutInflater().inflate(R.layout.rowjson, null);

        }

        TextView name = (TextView)convertView.findViewById(R.id.nameText);

        try{
            JSONObject json = friends.getJSONObject(position);
            name.setText(json.getString("name"));

        }catch (JSONException jse){
            jse.printStackTrace();
        }

        return convertView;
    }

}
