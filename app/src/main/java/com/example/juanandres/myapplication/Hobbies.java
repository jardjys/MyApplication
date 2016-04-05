package com.example.juanandres.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hobbies.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hobbies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hobbies extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Activity activity;
    EditText input;
    TextView text;
    private final String FILE = "myProperties.xml";
    Properties properties;



    private OnFragmentInteractionListener mListener;

    public Hobbies() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hobbies.
     */
    // TODO: Rename and change types and number of parameters
    public static Hobbies newInstance(String param1, String param2) {
        Hobbies fragment = new Hobbies();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hobbies, container, false);
        text = (TextView)v.findViewById(R.id.hobby);
        input = (EditText)v.findViewById(R.id.editText);
        Button button = (Button)v.findViewById(R.id.savehobby);
        button.setOnClickListener(this);

        File file = new File(this.activity.getFilesDir(), FILE);
        properties = new Properties();

        try {
            if(file.exists()){
                FileInputStream fis = this.activity.openFileInput(FILE);
                properties.loadFromXML(fis);
                loadFromProperty();
            } else {
                saveToDevice();
            }
        }catch(IOException ioe) {
            Log.e("MAIN", ioe.toString());
        }


        return v;
    }


    private void saveToDevice() {
        try {
            FileOutputStream fos = this.activity.openFileOutput(FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromProperty(){
        text.setText(properties.getProperty("hobby"));
    }

    public void saveToProperty(){
        properties.setProperty("hobby", input.getText().toString());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
