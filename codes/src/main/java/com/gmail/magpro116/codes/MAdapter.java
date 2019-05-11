package com.gmail.magpro116.codes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MAdapter extends ArrayAdapter<SData>{

    private Context context;
    private List<SData> list;

    public MAdapter(Context context, List<SData> list) {
        super(context, android.R.layout.simple_spinner_item, list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position, convertView, parent);
    }

    private View getImageForPosition(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_spinner, parent, false);
        TextView textView =  row.findViewById(R.id.txtCity);

        SData data = list.get(position);
        textView.setText(data.getName());


        return row;
    }
}
