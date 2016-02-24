package com.example.android.online2;



import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;


public class GradeActivity extends Fragment{
    private ListView grades;
    private gradelistadapter adapter;
    private List<grades> gradesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.listview, null);

        grades = (ListView) v.findViewById(R.id.gradeview);

        gradesList=new ArrayList<>();
        gradesList.add(new grades(1,"GRADES","","",""));
        RequestQueue r= Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://10.192.40.165:8000/default/grades.json";
        StringRequest si = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {

                    JSONObject j = new JSONObject(s);

                    JSONArray k =j.getJSONArray("courses");
                    JSONArray w =j.getJSONArray("grades");
                    for(int y=0;y<w.length();y++){

                        String p=k.getJSONObject(y).getString("code");
                        String p1=w.getJSONObject(y).getString("name");
                        String p2= "score - " +Integer.toString(w.getJSONObject(y).getInt("score"))+"/"+Integer.toString(w.getJSONObject(y).getInt("out_of"));
                        String p3= "weightage"+" - "+Integer.toString(w.getJSONObject(y).getInt("weightage"));
                        gradesList.add(new grades(1,p,p1,p2,p3));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        },getActivity());
        adapter=new gradelistadapter(getActivity(),gradesList);
        grades.setAdapter(adapter);
        r.add(si);
        return v;
    }

}