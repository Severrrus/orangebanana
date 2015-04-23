package com.example.helpers;

import models.ChickenListViewModel;

import com.example.chickentime.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewChickensAdapter extends BaseAdapter {

    Context context;
    ChickenListViewModel[] data;
    private static LayoutInflater inflater = null;

    public ListViewChickensAdapter(Context context, ChickenListViewModel[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.item_chicken, null);
        TextView textViewStart = (TextView) vi.findViewById(R.id.itemChickenStart);
        TextView textViewElapsedTime = (TextView) vi.findViewById(R.id.itemChickenElapsedTime);
        TextView textViewRemainingTime = (TextView) vi.findViewById(R.id.itemChickenRemainingTime);
        LinearLayout linearLayoutChicken = (LinearLayout) vi.findViewById(R.id.linearLayoutChicken);
        
        /*if(this.data[position].isSurvived()){
        	linearLayoutChicken.setBackgroundColor(Color.GREEN);
        }else{
        	linearLayoutChicken.setBackgroundColor(Color.RED);
        }
        */
        textViewStart.setText(this.data[position].getStart());
        textViewElapsedTime.setText(this.data[position].getElapsedTime());
        textViewRemainingTime.setText(this.data[position].getRemainingTime());
        
        return vi;
    }
}