package com.example.chickentime.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chickentime.R;

public class TabFragmentStrongestChicken extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.tab_strongest_chicken, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue.dfont");
        TextView textViewStrongest = (TextView) rootView.findViewById(R.id.strongest);
        TextView textViewChicken = (TextView) rootView.findViewById(R.id.chicken);
        TextView textViewTimeSaved = (TextView) rootView.findViewById(R.id.timeSaved);
        textViewStrongest.setTypeface(myTypeface);
        textViewChicken.setTypeface(myTypeface);
        textViewTimeSaved.setTypeface(myTypeface);
        
        return rootView;
    }
}
