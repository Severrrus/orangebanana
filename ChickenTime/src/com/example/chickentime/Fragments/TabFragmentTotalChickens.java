package com.example.chickentime.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chickentime.R;

public class TabFragmentTotalChickens extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.tab_total_chickens, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue.dfont");
        TextView textViewTotal = (TextView) rootView.findViewById(R.id.total);
        TextView textViewChickens = (TextView) rootView.findViewById(R.id.chickens);
        TextView textViewTotalAmount = (TextView) rootView.findViewById(R.id.totalAmount);
        textViewTotal.setTypeface(myTypeface);
        textViewChickens.setTypeface(myTypeface);
        textViewTotalAmount.setTypeface(myTypeface);
        
        return rootView;
    }
}
