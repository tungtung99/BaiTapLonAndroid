package com.example.tuvungtienganh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuvungtienganh.Models.ChuDe;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<ChuDe> contents;

    public CustomAdapter(Context context, ArrayList<ChuDe> contents) {
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
        ChuDe p = (ChuDe) getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt1 = (TextView) view.findViewById(R.id.TextViewName);
            txt1.setText(p.getName());

            byte[] Image = p.getAnh();
            ImageView img1 = (ImageView) view.findViewById(R.id.imageWords);
            Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
            img1.setImageBitmap(bitmap);
        }
        return view;
    }

}
