package com.example.user.projectbidanku.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.projectbidanku.AppConfiguration.RealmHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.FullScreenActivity;
import com.example.user.projectbidanku.Model.Foto;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.R;

import java.io.File;
import java.util.List;

/**

 */
public class FotoCalonBayiRecyclerViewAdapter extends RecyclerView.Adapter<FotoCalonBayiRecyclerViewAdapter.ViewHolder> {

    private final List<Foto> mValues;
    private Context context;
    private SessionManager sessionManager;

    public FotoCalonBayiRecyclerViewAdapter(List<Foto> items, Context context) {
        this.mValues = items;
        this.context = context;
        this.sessionManager = new SessionManager(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_camera, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//            if (getItemCount() - 1 >= 0 + (position * 3)) {
//                holder.imageView1.setImageURI(Uri.fromFile(new File(mValues.get(0 + (position * 3)).getAlamat())));
//            }
//            if (getItemCount() - 1 >= 1 + (position * 3)) {
//                holder.imageView2.setImageURI(Uri.fromFile(new File(mValues.get(1 + (position * 3)).getAlamat())));
//            }
//            if (getItemCount() - 1 >= 2 + (position * 3)) {
//                holder.imageView3.setImageURI(Uri.fromFile(new File(mValues.get(2 + (position * 3)).getAlamat())));
//            }
//        holder.imageView1.setImageURI(Uri.fromFile(new File(mValues.get(position).getAlamat())));
        Glide.with(context)
                .load(mValues.get(position).getAlamat())
                .override(800, 200)
                .centerCrop()
                .into(holder.imageView1);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,FullScreenActivity.class);
                i.putExtra("path",mValues.get(position).getAlamat());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final ImageView imageView1,imageView2,imageView3;
        public final ImageView imageView1;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView1 = (ImageView) view.findViewById(R.id.imageview1);
//            imageView2 = (ImageView) view.findViewById(R.id.imageview2);
//            imageView3 = (ImageView) view.findViewById(R.id.imageview3);
        }

    }
}
