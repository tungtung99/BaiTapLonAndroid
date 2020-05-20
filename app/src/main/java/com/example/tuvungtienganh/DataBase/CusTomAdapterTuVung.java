package com.example.tuvungtienganh.DataBase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuvungtienganh.Content;
import com.example.tuvungtienganh.Models.TuVung;
import com.example.tuvungtienganh.R;
import com.example.tuvungtienganh.Tu_Vung;

import java.util.ArrayList;

public class CusTomAdapterTuVung extends BaseAdapter{
    private Context context;
    private ArrayList<TuVung> contents;

    public CusTomAdapterTuVung(Context context, ArrayList<TuVung> contents) {
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
        TuVung p = (TuVung) getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt1 = (TextView) view.findViewById(R.id.TextViewName);
            txt1.setText(p.getTuVung());

//            ImageView img1 = (ImageView) view.findViewById(R.id.imageWords);
//            int abc = p.getImage();
//            img1.setImageResource(abc);
        }
        return view;
    }

}

