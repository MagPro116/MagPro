package com.gmail.magpro116.codes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CityAdapter extends ArrayAdapter<Integer>{

    private Integer[] images;
    private String[] text;
    private Context context;

    public CityAdapter(Context context, Integer[] images, String[] text) {
        super(context, android.R.layout.simple_spinner_item, images);
        this.images = images;
        this.text=text;
        this.context=context;
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
        View row = inflater.inflate(R.layout.spinner_value_layout, parent, false);
        TextView textView =  row.findViewById(R.id.txtCity);
        textView.setText(text[position]);
        ImageView imageView = row.findViewById(R.id.imgFlag);
        imageView.setImageResource(images[position]);
        return row;
    }
}
