package com.example.chickentime;

import models.ChickenListViewModel;

import com.example.helpers.ListViewChickensAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewChickens extends Activity {
    ListView listView ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_layout);
        
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listViewChickens);
        
        // Defined Array values to show in ListView
        ChickenListViewModel chicken1 = new ChickenListViewModel();
        ChickenListViewModel chicken2 = new ChickenListViewModel();
        ChickenListViewModel chicken3 = new ChickenListViewModel();
        ChickenListViewModel chicken4 = new ChickenListViewModel();
        ChickenListViewModel chicken5 = new ChickenListViewModel();
        ChickenListViewModel chicken6 = new ChickenListViewModel();
        ChickenListViewModel chicken7 = new ChickenListViewModel();
        ChickenListViewModel chicken8 = new ChickenListViewModel();
        ChickenListViewModel chicken9 = new ChickenListViewModel();
        ChickenListViewModel chicken10 = new ChickenListViewModel();
        
        chicken1.setStart("2:00:00");
        chicken1.setElapsedTime("1:20:00");
        chicken1.setRemainingTime("0:40:00");
        chicken1.setSurvived(false);
        chicken2.setStart("0:20:00");
        chicken2.setElapsedTime("0:20:00");
        chicken2.setRemainingTime("0:00:00");
        chicken2.setSurvived(true);
        chicken3.setStart("0:45:00");
        chicken3.setElapsedTime("0:45:00");
        chicken3.setRemainingTime("0:00:00");
        chicken3.setSurvived(true);
        chicken4.setStart("2:00:00");
        chicken4.setElapsedTime("1:20:00");
        chicken4.setRemainingTime("0:40:00");
        chicken4.setSurvived(false);
        chicken5.setStart("1:30:00");
        chicken5.setElapsedTime("1:20:00");
        chicken5.setRemainingTime("0:10:00");
        chicken5.setSurvived(false);
        chicken6.setStart("2:00:00");
        chicken6.setElapsedTime("1:20:00");
        chicken6.setRemainingTime("0:40:00");
        chicken6.setSurvived(false);
        chicken7.setStart("0:20:00");
        chicken7.setElapsedTime("0:20:00");
        chicken7.setRemainingTime("0:00:00");
        chicken7.setSurvived(true);
        chicken8.setStart("0:45:00");
        chicken8.setElapsedTime("0:45:00");
        chicken8.setRemainingTime("0:00:00");
        chicken8.setSurvived(true);
        chicken9.setStart("2:00:00");
        chicken9.setElapsedTime("1:20:00");
        chicken9.setRemainingTime("0:40:00");
        chicken9.setSurvived(false);
        chicken10.setStart("1:30:00");
        chicken10.setElapsedTime("1:20:00");
        chicken10.setRemainingTime("0:10:00");
        chicken10.setSurvived(false);
        ChickenListViewModel[] values = new ChickenListViewModel[] { chicken1, chicken2, chicken3, chicken4, chicken5, chicken6, chicken7 , chicken8, chicken9, chicken10};

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

     


        // Assign adapter to ListView
        listView.setAdapter(new ListViewChickensAdapter(this, values));
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}}); 
    }

}
