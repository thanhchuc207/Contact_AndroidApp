package com.example.contactappv1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Contact> contactList; //ArrayList chứa dữ liệu để đổ lên recyclerview
    public CustomAdapter(ArrayList<Contact> contactList)
    {
        this.contactList=contactList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact,parent,false);   // R.layout.contact là đường dẫn tới file xml của item thuộc //list đã thiết kế ở B4
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(contactList.get(position).getName());

        char firstChar = holder.tvName.getText().toString().charAt(0);
        holder.tvImg.setText(String.valueOf(firstChar));

        /*
        int currentColor=contactList.get(position).getColor();

        if(currentColor==0) {
            int color=getRandomColor();
            holder.tvImg.setBackgroundColor(color);
            contactList.get(position).setColor(color);
        }
        else
        {
            holder.tvImg.setBackgroundColor(currentColor);
        }

         */
    }

    @Override
    public int getItemCount() {

        return contactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvImg;
        public TextView tvName;
        public ViewHolder(View view)
        {	super(view);
            tvName=(TextView) view.findViewById(R.id.tvName);
            tvImg=(TextView) view.findViewById(R.id.tvImg);
        }
    }
    private int getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return Color.rgb(r, g, b);
    }

}
