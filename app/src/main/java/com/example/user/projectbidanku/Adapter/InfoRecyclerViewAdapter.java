package com.example.user.projectbidanku.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.projectbidanku.Model.Info;
import com.example.user.projectbidanku.R;

import java.util.List;

/**

 */
public class InfoRecyclerViewAdapter extends RecyclerView.Adapter<InfoRecyclerViewAdapter.ViewHolder> {

    private final List<Info> mValues;
    private Context context;

    public InfoRecyclerViewAdapter(List<Info> items, Context context) {
        this.mValues = items;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImageView.setImageResource(holder.mItem.getImg());
        holder.mContentKeterangan.setText(holder.mItem.getContent(1));
        holder.mContentView.setText(holder.mItem.getJudul());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mContentView;
        public final TextView mContentKeterangan;
        public final ImageView mImageView;

        public Info mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mContentView = (TextView) view.findViewById(R.id.txt_judul);
            mContentKeterangan = (TextView) view.findViewById(R.id.txt_content);
            mImageView = (ImageView) view.findViewById(R.id.img_info);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
