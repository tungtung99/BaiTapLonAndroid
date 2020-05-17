package com.example.tuvungtienganh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Content> contents;

    public CustomAdapter(Context context, ArrayList<Content> contents) {
        this.context = context;
        this.contents = contents;
    }
    @Override
    public int getCount() {
        return contents.size();
    }
    @Override
    public Object getItem(int position) {
        return contents.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view =  inflater.inflate(R.layout.activity_line_tuvung, null);
        }
        Content p = (Content) getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt1 = (TextView) view.findViewById(R.id.TextViewName);
            txt1.setText(p.getName());

            ImageView img1 = (ImageView) view.findViewById(R.id.imageWords);
            int abc = p.getImage();
            img1.setImageResource(abc);
        }
        return view;
    }

}
