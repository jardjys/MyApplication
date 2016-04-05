package com.example.juanandres.myapplication;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by JuanAndres on 08/03/2016.
 */
public class JSONRequest extends AsyncTask<String, Void, JSONArray> {
    JSONRequestListener listener;

    public JSONRequest(JSONRequestListener listener){
        this.listener = listener;
    }

    // actual task
    @Override
    protected JSONArray doInBackground(String... params) {

        JSONArray result = null;
        URLConnection connection = null;
        InputStream stream = null;

        try {

            // http://jsonplaceholder.typicode.com/posts
            URL url = new URL(params[0]);
            connection = (URLConnection)url.openConnection();

            connection.connect();

            stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            String line = "";
            StringBuilder builder = new StringBuilder();

            while((line = br.readLine()) != null){
                builder.append(line);
            }

            result = new JSONArray(builder.toString());

        }catch(Exception e){
            Log.e("JSONRequest", e.toString());
        }finally{

            try{
                if(stream != null) stream.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return result;
    }

    @Override
    protected void onPostExecute(JSONArray array) {
        super.onPostExecute(array);
        listener.jSONRequestComplete(array);
    }

    // use this for callback
    public interface JSONRequestListener{
        void jSONRequestComplete(JSONArray jsonArray);
    }
}