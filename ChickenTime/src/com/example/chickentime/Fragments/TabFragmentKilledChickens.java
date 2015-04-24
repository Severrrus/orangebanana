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

public class TabFragmentKilledChickens extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		SharedPreferences sharedPref = MainActivity.main.getSharedPreferences(
				"com.example.chickentime", Context.MODE_PRIVATE);
		int killedChickens = sharedPref.getInt("killed", 0);
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.tab_killed_chickens, container, false);

		Typeface myTypeface = Typeface.createFromAsset(getActivity()
				.getAssets(), "fonts/HelveticaNeue.dfont");
		TextView textViewKlled = (TextView) rootView.findViewById(R.id.killed);
		TextView textViewChickens = (TextView) rootView
				.findViewById(R.id.chickens);
		TextView textViewKilledAmount = (TextView) rootView
				.findViewById(R.id.killedAmount);
		textViewKlled.setTypeface(myTypeface);
		textViewChickens.setTypeface(myTypeface);
		textViewKilledAmount.setTypeface(myTypeface);
		textViewKilledAmount.setText(Integer.toString(killedChickens));

		return rootView;
	}
}
