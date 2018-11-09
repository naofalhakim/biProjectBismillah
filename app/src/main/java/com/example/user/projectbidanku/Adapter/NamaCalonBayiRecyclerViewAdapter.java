package com.example.user.projectbidanku.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.projectbidanku.Model.Info;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.R;

import java.util.List;

/**

 */
public class NamaCalonBayiRecyclerViewAdapter extends RecyclerView.Adapter<NamaCalonBayiRecyclerViewAdapter.ViewHolder> {

    private final List<NamaCalonBayi> mValues;
    private Context context;

    public NamaCalonBayiRecyclerViewAdapter(List<NamaCalonBayi> items, Context context) {
        this.mValues = items;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_nama, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(holder.mItem.getNama());
        holder.mContentKeterangan.setText(holder.mItem.getArti());
        if (holder.mItem.getJeniskelamin().equals("L")){
            holder.mImageView.setText("L");
        }else{
            holder.mImageView.setText("P");
        }

        if(holder.mItem.getSign_fav() == 0) {
            holder.imageView.setImageResource(R.drawable.ic_male_baby);
        }else{
            holder.imageView.setImageResource(R.drawable.ic_female_baby);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mItem.getSign_fav() == 0){
                    holder.mItem.setSign_fav(1);
                    holder.imageView.setImageResource(R.drawable.ic_female_baby);
                    Toast.makeText(context,"Added to Favorit",Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        public final TextView mImageView;
        public final ImageView imageView;

        public NamaCalonBayi mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.txt_nama_bayi);
            mContentKeterangan = (TextView) view.findViewById(R.id.txt_arti);
            mImageView = (TextView) view.findViewById(R.id.img_info1);
            imageView = (ImageView) view.findViewById(R.id.img_info);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
