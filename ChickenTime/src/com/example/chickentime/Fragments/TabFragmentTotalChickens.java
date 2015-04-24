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

public class TabFragmentTotalChickens extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	SharedPreferences sharedPref = MainActivity.main.getSharedPreferences(
				"com.example.chickentime", Context.MODE_PRIVATE);
		int savedChickens = sharedPref.getInt("saved", 0);
		int killedChickens = sharedPref.getInt("killed", 0);
		int savedTime = sharedPref.getInt("savedTime", 0);
    	
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.tab_total_chickens, container, false);

        
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue.dfont");
        TextView textViewSurvivedChickensAmount = (TextView) rootView.findViewById(R.id.totalSurvivedChickensAmount);
        TextView textViewPercentageSavedToKill = (TextView) rootView.findViewById(R.id.percentageSavedToKill);
        TextView textViewKilledChickensAmount = (TextView) rootView.findViewById(R.id.totalKilledChickensAmount);
        TextView textViewTimeSavedThisWeek = (TextView) rootView.findViewById(R.id.timeSavedThisWeekAmount);
        TextView textViewTimeSavedTotalAmount = (TextView) rootView.findViewById(R.id.timeSavedTotalAmount);
        TextView textViewChickenSurvivabilityTag = (TextView) rootView.findViewById(R.id.chickenSurvivabilityTag);
        TextView textViewSurvivedTag = (TextView) rootView.findViewById(R.id.survivedTag);
        TextView textViewKilledTag = (TextView) rootView.findViewById(R.id.killedTag);
        TextView textViewSavedTimeTag = (TextView) rootView.findViewById(R.id.savedTimeTag);
        TextView textViewThisWeekTag = (TextView) rootView.findViewById(R.id.thisWeekTag);
        TextView textViewTotalTag = (TextView) rootView.findViewById(R.id.totalTag);


        textViewSurvivedChickensAmount.setTypeface(myTypeface);
        textViewPercentageSavedToKill.setTypeface(myTypeface);
        textViewKilledChickensAmount.setTypeface(myTypeface);
        textViewTimeSavedThisWeek.setTypeface(myTypeface);
        textViewTimeSavedTotalAmount.setTypeface(myTypeface);
        textViewChickenSurvivabilityTag.setTypeface(myTypeface);
        textViewSurvivedTag.setTypeface(myTypeface);
        textViewKilledTag.setTypeface(myTypeface);
        textViewSavedTimeTag.setTypeface(myTypeface);
        textViewThisWeekTag.setTypeface(myTypeface);
        textViewTotalTag.setTypeface(myTypeface);
        
        
       textViewKilledChickensAmount.setText(Integer.toString(killedChickens));
       textViewSurvivedChickensAmount.setText(Integer.toString(savedChickens));
       textViewPercentageSavedToKill.setText(Float.toString((100f * savedChickens) / (savedChickens + killedChickens)).replaceFirst(".0$", "") + '%' );
       textViewTimeSavedTotalAmount.setText(Integer.toString(savedTime / 3600)+"h "+Integer.toString(savedTime/60) + "min");
       
       return rootView;
    }
}
