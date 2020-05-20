package com.example.tuvungtienganh;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;

public class TuVungCustomAdapter extends ResourceCursorAdapter {
    public TuVungCustomAdapter(Context context, int layout, Cursor c,int flags) {
        super(context, layout, c,flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView anh= view.findViewById(R.id.imageViewtu);
    }
}
