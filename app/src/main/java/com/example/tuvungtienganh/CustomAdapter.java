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
    private Context context; //context
    private ArrayList<Words> words;

    public CustomAdapter(Context context, ArrayList<Words> words) {
        this.context = context;
        this.words = words;
    }
    @Override
    public int getCount() {
        return words.size();
    }
    @Override
    public Object getItem(int position) {
        return words.get(position);
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
        Words p = (Words)getItem(position);
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
