package com.example.chickentime.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chickentime.MainActivity;
import com.example.chickentime.R;

public class TabFragmentStrongestChicken extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.tab_strongest_chicken, container, false);

        SharedPreferences sharedPref = MainActivity.main.getSharedPreferences(
				"com.example.chickentime", Context.MODE_PRIVATE);
		int longestChickens = sharedPref.getInt("longest", 0);
        
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue.dfont");
        TextView textViewStrongest = (TextView) rootView.findViewById(R.id.strongest);
        TextView textViewChicken = (TextView) rootView.findViewById(R.id.chicken);
        TextView textViewTimeSaved = (TextView) rootView.findViewById(R.id.timeSaved);
        textViewStrongest.setTypeface(myTypeface);
        textViewChicken.setTypeface(myTypeface);
        textViewTimeSaved.setTypeface(myTypeface);
        
        textViewTimeSaved.setText(Integer.toString(longestChickens/3600) + "h " + Integer.toString((longestChickens%3600) / 60)+"min");
        return rootView;
    }
}
