package com.example.android.online2;



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
import android.widget.TextView;
import android.widget.Toast;


public class Overview extends Fragment{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_overview,null);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView email = (TextView) v.findViewById(R.id.email);
        TextView entry_no = (TextView) v.findViewById(R.id.entry_no);

        entry_no.setText(getArguments().getString("entry_no"));
        name.setText(getArguments().getString("name"));
        email.setText(getArguments().getString("email"));




        return v;

    }


}